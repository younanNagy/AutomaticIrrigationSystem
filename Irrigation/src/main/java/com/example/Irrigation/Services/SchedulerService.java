package com.example.Irrigation.Services;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.Irrigation.Job.SampleCronJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.example.Irrigation.Component.JobScheduleCreator;
import com.example.Irrigation.Repos.IrrigationPlanRepo;
import com.example.Irrigation.models.IrrigationPlan;
//import com.example.Irrigation.models.SchedulerJobInfo;
//import com.stackabuse.job.SampleCronJob;

//import com.stackabuse.repository.SchedulerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class SchedulerService {

        @Autowired
        private Scheduler scheduler;
    private JobDetail buildJobDetail(Long irrigation_plan_id,String job_name) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("irrigation_plan_id",irrigation_plan_id.toString());


        return JobBuilder.newJob(SampleCronJob.class)
                .withIdentity(job_name, "IrrigationPlan-jobs")
                .withDescription("IrrigationPlan Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }
    private CronTrigger buildJobTrigger(JobDetail jobDetail,String cron) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "IrrigationPlan-triggers")
                .withDescription("IrrigationPlan Trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
    }
    public boolean scheduleNewJob(Long irrigation_plan_id, String  job_name,String cron)
    {
        try {
            JobDetail job = buildJobDetail(irrigation_plan_id,job_name);
            CronTrigger trigger = buildJobTrigger(job, cron);
            scheduler.scheduleJob(job, trigger);

            scheduler.start();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }
//0/10 * * ? * * *
}



