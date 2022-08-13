package com.example.Irrigation.Job;
//import java.util.stream.IntStream;

import com.example.Irrigation.Services.PlotService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisallowConcurrentExecution
public class SampleCronJob extends QuartzJobBean {
    @Autowired
    PlotService plot_service;
    @Override
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("SampleCronJob Start................");
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String irrigation_plan_id = jobDataMap.getString("irrigation_plan_id");
        plot_service.irrigatePlot(Long.parseLong(irrigation_plan_id));

        log.info("SampleCronJob End................");
    }
}
