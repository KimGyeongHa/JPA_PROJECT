package jpaShop.shop.job;

import jpaShop.shop.job.file.FileData;
import jpaShop.shop.job.file.FileDataSetMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class FileDataReaderConfing {

    private static final int CHUNCK_SIZE = 5;

    @Bean
    public Job fileDataReadJob(JobRepository jobRepository,
                               @Qualifier(value = "flatFileDataReadStep") Step flatFileDataReadStep){
        return new JobBuilder("fileDataReadJob",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(flatFileDataReadStep)
                .build();
    }

    @Bean
    @Qualifier(value = "flatFileDataReadStep")
    public Step flatFileDataReadStep(JobRepository jobRepository,
                                 @Qualifier(value = "flatFileDataReader") ItemReader flatFileDataReader,
                                 PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("fileDataReadStep",jobRepository)
                .<FileData,FileData>chunk(CHUNCK_SIZE,platformTransactionManager)
                .reader(flatFileDataReader)
                .writer(chunk -> {
                    chunk.getItems().stream().forEach(item -> System.out.println("FILE DATA READER : " + item));
                })
                .build();
    }

    @Bean
    @Qualifier(value = "flatFileDataReader")
    public FlatFileItemReader<FileData> flatFileDataReader() throws Exception {
        return new FlatFileItemReaderBuilder<FileData>()
                .name("fileDataReader")
                .resource(new FileSystemResource("cat/src/main/resources/templates/csvFile.csv"))
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
