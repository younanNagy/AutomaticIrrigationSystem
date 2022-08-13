package com.example.Irrigation.Controllers;


import com.example.Irrigation.Services.SchedulerService;
import com.example.Irrigation.models.IrrigationPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Irrigation.Services.PlotService;
import com.example.Irrigation.models.Plot;

import java.util.List;

@RestController
public class PlotRestController {

    @Autowired
    PlotService plot_service;

    @Autowired
    SchedulerService scheduler_service;

    @RequestMapping(value = "/plots",method = RequestMethod.GET)
    public List<Plot>getAllPlots() {
        return plot_service.listAllPlots();
    }

//    @RequestMapping(value = "/plots",method = RequestMethod.GET)
//    public ResponseEntity<List<Plot>>getAllPlots() {
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(plot_service.listAllPlots());
//    }

    @RequestMapping(value = "/plots/{id}",method = RequestMethod.GET)
    public Plot getPlot(@PathVariable("id") Long id) {return plot_service.getByID(id);}

    @RequestMapping(value = "/plots", method = RequestMethod.POST)
    public Plot addPlot(@RequestBody Plot plot_to_be_added) {
        return plot_service.addPlot(plot_to_be_added);
    }

    @RequestMapping(value = "/plots", method = RequestMethod.PUT)
    public Plot updatePlot(@RequestBody Plot plot_to_be_added)
    {
        return plot_service.upsertPlot(plot_to_be_added);
    }


    @RequestMapping(value = "/plots/{id}/irrigation_plans", method = RequestMethod.POST)
    boolean addIrrigationPlan(@RequestBody IrrigationPlan irrigation_plan,@PathVariable("id") Long plot_id)
    {

//        System.out.println(irrigation_plan);
//        System.out.println(irrigation_plan.getName());
//        System.out.println(irrigation_plan.getType());
//
        return plot_service.scheduleIrrigationPlanForPlot(plot_id,irrigation_plan);
    }


//    curl -H "Content-type: application/json" -X POST -d "{\"area\": \"america\"}" "http://localhost:8080/plots/"

    //curl -H "Content-type: application/json" -X POST -d "{\"name\":\"name\", \"group\":\"group\"}" "http://localhost:8080/plots/1/irrigation_plans"

}
