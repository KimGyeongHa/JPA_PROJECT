package findAnyCat.cat.job;

import findAnyCat.cat.image.Image;
import findAnyCat.cat.image.repository.ImageRepository;
import findAnyCat.cat.item.Item;
import findAnyCat.cat.item.repository.ItemRepsoitory;
import findAnyCat.cat.order.Order;
import findAnyCat.cat.order.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
public class ItemBatchConfig {

    private final ImageRepository imageRepository;
    private final EntityManagerFactory emf;


    private static final int CHUNK_SIZE = 5;


    @Bean
    public Job itemBatch(JobRepository jobRepository,Step itmeCopyStep){
        return new JobBuilder("itemBatch",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(itmeCopyStep)
                .build();
    }


    @Bean
    public Step itmeCopyStep(JobRepository jobRepository,ItemReader getItemListReader,ItemProcessor toItemProcessor,ItemWriter imageItemWriter,PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("itmeCopyStep",jobRepository)
                .<Item,Image>chunk(CHUNK_SIZE,platformTransactionManager)
                .reader(getItemListReader)
                .processor(toItemProcessor)
                .writer(imageItemWriter)
                .build();
    }


    @Bean
    public JpaPagingItemReader<Item> getItemListReader(){
        return new JpaPagingItemReaderBuilder<Item>()
                .queryString("select m from Item m")
                .pageSize(CHUNK_SIZE)
                .entityManagerFactory(emf)
                .name("getItemList")
                .build();
    }

    @Bean
    public ItemProcessor<Item,Image> toItemProcessor(){
        return new ItemProcessor<Item, Image>() {
            @Override
            public Image process(Item item) throws Exception {
                return new Image(item);
            }
        };
    }

    @Bean
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
