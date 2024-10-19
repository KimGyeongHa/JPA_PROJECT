package com.shop.domain.provide.domain.job;

import com.shop.domain.provide.domain.image.Image;
import com.shop.domain.provide.domain.image.repository.ImageRepository;
import com.shop.domain.provide.domain.item.Item;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
public class ItemBatchConfig {

    private final ImageRepository imageRepository;
    private final EntityManagerFactory emf;

    private static final int CHUNK_SIZE = 5;

    @Bean
    public Job itemBatch(JobRepository jobRepository,
                         @Qualifier(value = "itemCopyStep") Step itmeCopyStep){
        return new JobBuilder("itemBatch",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(itmeCopyStep)
                .build();
    }

    @Bean
    @JobScope
    @Qualifier(value = "itemCopyStep")
    public Step itemCopyStep(JobRepository jobRepository,
                             @Qualifier(value = "getItemListReader") ItemReader getItemListReader,
                             ItemProcessor toItemProcessor,
                             ItemWriter imageItemWriter,
                             PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("itemCopyStep",jobRepository)
                .<Item, Image>chunk(CHUNK_SIZE,platformTransactionManager)
                .reader(getItemListReader)
                .processor(toItemProcessor)
                .writer(imageItemWriter)
                .build();
    }

    @Bean
    @StepScope
    @Qualifier(value = "getItemListReader")
    public JpaPagingItemReader<Item> getItemListReader(){
        return new JpaPagingItemReaderBuilder<Item>()
                .queryString("select m from Item m")
                .pageSize(CHUNK_SIZE)
                .entityManagerFactory(emf)
                .name("getItemList")
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Item,Image> toItemProcessor(){
        return new ItemProcessor<Item, Image>() {
            @Override
            public Image process(Item item) throws Exception {
                return new Image(item);
            }
        };
    }

    @Bean
    @StepScope
    public ItemWriter<Image> imageItemWriter(){
        return new ItemWriter<Image>() {
            @Override
            public void write(Chunk<? extends Image> chunk) throws Exception {
               chunk.getItems().stream().forEach(item -> imageRepository.insertImages(item));
            }
        };
    }

    /*
        @Bean
        public RepositoryItemReader<Item> itemRepositoryItemReader(){
            return new RepositoryItemReaderBuilder<Item>()
                    .repository(ItemRepsoitory)
                    .methodName()
        }
    */



}
