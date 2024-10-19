package com.shop.web.schedule.util.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("messageSchedule")
public class MessageScheduled {

    public void scheduleStart() {
        try {
            Scheduler scheduled = StdSchedulerFactory.getDefaultScheduler();

            JobDetail jobDetail = JobBuilder
                    .newJob(MessageJob.class)
                    .withIdentity("messageJob")
                    .usingJobData("sender", "sender")
                    .build();

            List<Map<String, Object>> jobList = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                Map<String, Object> jobMap = new HashMap<>();
                jobMap.put("message", "안녕하세요" + i);
                jobList.add(jobMap);
            }

            jobDetail.getJobDataMap().put("message", jobList);

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
