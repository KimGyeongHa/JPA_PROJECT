package com.shop.batch.schedule.util.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

@Component("messageSchedule")
public class Scheduled {

    public void scheduleStart() {
        try {
            Scheduler scheduled = StdSchedulerFactory.getDefaultScheduler();

            JobDetail jobDetail = JobBuilder
                    .newJob(BatchJobToQuartz.class)
                    .withIdentity("batchJob")
                    .usingJobData("sender", "sender")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("jobTrigger", "message")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(10)
                            .repeatForever())
                    .build();

            scheduled.scheduleJob(jobDetail, trigger);

            scheduled.start();
        } catch (SchedulerException e) {

        }
    }

    public String cancelTargetSchedule(String targetName) {
        try {
            Scheduler scheduled = StdSchedulerFactory.getDefaultScheduler();
            JobKey jobKey = JobKey.jobKey(targetName);

            if (scheduled.checkExists(jobKey)) {
                scheduled.interrupt(jobKey);
                return "target cancel";
            }
        } catch (SchedulerException e) {

        }

        return "존재하지 않는 JOB";
    }
}
