package com.shop.batch.schedule.util.quartz;

import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("batchJobToQuartz")
@RequiredArgsConstructor
public class BatchJobToQuartz extends QuartzJobBean {

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        /*JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("date", new Date());*/

        try {
           /* jobLauncher.run(jobRegistry.getJob("orderJob"), jobParametersBuilder.toJobParameters());*/

            System.out.println("2132131321321 ");
        }catch (Exception e){}
    }
}
