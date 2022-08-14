package com.example.Irrigation.Services;


import com.example.Irrigation.Repos.IrrigationPlanExecutionHistoryRepo;
import com.example.Irrigation.Repos.IrrigationPlanRepo;
import com.example.Irrigation.models.IrrigationPlan;
import com.example.Irrigation.models.IrrigationPlanExecutionHistory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.Irrigation.models.Plot;
import com.example.Irrigation.Repos.PlotRepo;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PlotService {

    @Autowired
    PlotRepo plot_repo;
    @Autowired
    IrrigationPlanExecutionHistoryRepo irrigationPlanExecutionHistoryRepo;
    @Autowired
    IrrigationPlanRepo irrigation_plan_repo;
    @Autowired
    SchedulerService scheduler;
    public Plot addPlot(Plot new_plot) {
        return plot_repo.save(new_plot);
    }
    public Plot getByID(Long plot_id) {return plot_repo.findById(plot_id).get();}

    public Plot upsertPlot(Plot plot)
    {

        Plot existing_plot=plot_repo.findById(plot.getId()).get();
        if(existing_plot ==null)
            return plot_repo.save(plot);

        existing_plot.updatePlotData(plot);
        return plot_repo.save(existing_plot);
    }
    public List<Plot>listAllPlots() {
        return plot_repo.findAll();
    }

    private IrrigationPlan AddIrrigationPlanIfNotExists(IrrigationPlan irrigation_plan,Plot plot_to_be_irrigated) {
        IrrigationPlan existing_irrigation_plan = irrigation_plan_repo.findByName(irrigation_plan.getName());

        if (existing_irrigation_plan == null) {
            irrigation_plan.setPlot_to_be_irrigated(plot_to_be_irrigated);
            irrigation_plan.setScheduled(false);
            irrigation_plan_repo.save(irrigation_plan);
            return irrigation_plan;
        } else {
            existing_irrigation_plan.setPlot_to_be_irrigated(plot_to_be_irrigated);
            irrigation_plan_repo.save(existing_irrigation_plan);
            return existing_irrigation_plan;
        }
    }
    public boolean scheduleIrrigationPlanForPlot(Long plot_id, IrrigationPlan irrigation_plan)
    {
        Plot plot_to_be_irrigated=getByID(plot_id);
        if(plot_to_be_irrigated==null)
            return false;
        irrigation_plan=AddIrrigationPlanIfNotExists(irrigation_plan,plot_to_be_irrigated);
        if(irrigation_plan.isScheduled())
            return false;

        if(scheduler.scheduleNewJob(plot_id,plot_to_be_irrigated.getPlot_name()+","+irrigation_plan.getName(),irrigation_plan.getCronExpression())){
            irrigation_plan.setScheduled(true);
            return true;
        }
        else {
            irrigation_plan.setScheduled(false);
            return false;
        }

    }
    public String irrigatePlot(Long irrigation_plan_id)
    {

        String iot_device_status="";
        IrrigationPlan plan=  irrigation_plan_repo.findById(irrigation_plan_id).get();
        Plot plot=plan.getPlot_to_be_irrigated();
        RestTemplate iot_caller = new RestTemplate();

        for(int i=0;i<3;i++)
        {
            iot_device_status=iot_caller.getForObject("http://localhost:8090/checker/"+plot.getIot_device_id(),String.class);
            if(iot_device_status.equals("Available"))
                break;
        }
        IrrigationPlanExecutionHistory transaction=new IrrigationPlanExecutionHistory();
        transaction.setIrrigation_plan(plan);
        transaction.setIrrigation_status(iot_device_status);
        irrigationPlanExecutionHistoryRepo.save(transaction);
        plot.setIot_device_status(iot_device_status);
        plot_repo.save(plot);
        //get the irrigation program details
        //call for irrigation with details
        //create transaction for irrigation slot history
        //create alarm for the failed iot
        return iot_device_status;
    }
}

//curl -H "Content-type: application/json" -X POST -d "{\"area\": \"america\",\"plot_name\": \"first\",\"iot_device_id\": \"1\"}" "http://localhost:8080/plots/"