package findAnyCat.cat.job;


import findAnyCat.cat.job.listener.JobLoggerListener;
import findAnyCat.cat.job.validator.Validating;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;

@Configuration
public class ValidatingParamJobConfig {

    @Bean
    public Job validatingParamJob(JobRepository jobRepository,Step oredrStep){
        return new JobBuilder("validatingParamJob",jobRepository)
                .start(oredrStep)
                .listener(new JobLoggerListener())
                .build();
    }

    /**
     * validate 값이 여러개일때는 이렇게 처리
     * @return CompositeJobParametersValidator
     */
    public CompositeJobParametersValidator multiValidator(){
        CompositeJobParametersValidator multiValidator = new CompositeJobParametersValidator();
        multiValidator.setValidators(Arrays.asList(new Validating()));
        return multiValidator;
    }


    @Bean
    public Step validatingParamStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("validatingParamStep",jobRepository)
                .tasklet(validatingParamTasklet(),platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet validatingParamTasklet(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                return RepeatStatus.FINISHED;
            }
        };
    }


}
