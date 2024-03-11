package findAnyCat.cat.job;

import findAnyCat.cat.job.file.FileData;
import findAnyCat.cat.job.file.FileDataSetMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class FileDataReaderConfing {

    private static final int CHUNCK_SIZE = 5;

    @Bean
    public Job fileDataReadJob(JobRepository jobRepository,
                               @Qualifier(value = "fileDataReadStep") Step fileDataReadStep){
        return new JobBuilder("fileDataReadJob",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(fileDataReadStep)
                .build();
    }

    @Bean
    @JobScope
    @Qualifier(value = "fileDataReadStep")
    public Step fileDataReadStep(JobRepository jobRepository,
                                 @Qualifier(value = "fileDataReader") ItemReader fileDataReader,
                                 PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("fileDataReadStep",jobRepository)
                .<FileData,FileData>chunk(CHUNCK_SIZE,platformTransactionManager)
                .reader(fileDataReader)
                .writer(new ItemWriter() {
                    @Override
                    public void write(Chunk chunk) throws Exception {
                        chunk.getItems().stream().forEach(item -> System.out.println("FILE DATA READER : " + item));
                    }
                })
                .build();
    }

    @Bean
    @StepScope
    @Qualifier(value = "fileDataReader")
    public FlatFileItemReader<FileData> fileDataReader(){
        return new FlatFileItemReaderBuilder<FileData>()
                .name("fileDataReader")
                .resource(new FileSystemResource("Players.csv"))
                .lineTokenizer(new DelimitedLineTokenizer())
                .fieldSetMapper(new FileDataSetMapper())
                .linesToSkip(1)
                .build();
    }

    /*@Bean
    public FlatFileItemWriter<FileData> fileDataWriter(){
        return new FlatFileItemWriterBuilder<FileData>()
                .name("fileDataWriter")
                .
    }*/

}
