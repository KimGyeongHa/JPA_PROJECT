package com.shop.batch.job;


import com.shop.batch.job.validator.Validating;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component("batchConfig")
public class BatchConfig {

    public Job orderJob(JobRepository jobRepository,
                        @Qualifier(value = "orderStep") Step orderStep){
        return new JobBuilder("orderJob",jobRepository)
                .start(orderStep)
                .validator(new Validating())
                .build();
    }

    @Qualifier(value = "orderStep")
    @JobScope
    public Step oredrStep(JobRepository jobRepository,
                          PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("orderStep",jobRepository)
                .tasklet(orderTasklet(),platformTransactionManager)
                .build();
    }

    @StepScope
    public Tasklet orderTasklet(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                return RepeatStatus.FINISHED;
            }
        };
    }

}
