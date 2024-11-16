package com.shop.batch.schedule.util.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;
import java.util.Map;

public class MessageJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<Map<String,Object>> jobList = (List<Map<String,Object>>)context.getMergedJobDataMap().get("message");

        for (Map<String,Object> job : jobList){
            System.out.println(job.getOrDefault("message","빈값"));
        }

    }
}
