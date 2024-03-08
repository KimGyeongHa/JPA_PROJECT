package findAnyCat.cat.job;

import findAnyCat.cat.image.Image;
import findAnyCat.cat.image.repository.ImageRepository;
import findAnyCat.cat.item.Item;
import findAnyCat.cat.item.repository.ItemRepsoitory;
import findAnyCat.cat.order.Order;
import findAnyCat.cat.order.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ItemBatchConfig {

    private final ItemRepsoitory itemRepsoitory;
    private final ImageRepository imageRepository;


    @Bean
    public Job itemBatch(JobRepository jobRepository,Step itmeCopyStep){
        return new JobBuilder("itemBatch",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(itmeCopyStep)
                .build();
    }


    @Bean
    @Primary
    public Step itmeCopyStep(JobRepository jobRepository,ItemReader itemReader,ItemProcessor toItemProcessor,ItemWriter imageItemWriter,PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("itmeCopyStep",jobRepository)
                .<Item,Image>chunk(5,platformTransactionManager)
                .reader(itemReader)
                .processor(toItemProcessor)
                .writer(imageItemWriter)
                .build();
    }


    @Bean
    public ItemReader<Item> itemReader(){
        return new ItemReader<Item>() {
            @Override
            public Item read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                Iterator<Item> items =  itemRepsoitory.findAll().iterator();
                return items.hasNext() ? items.next() : null;
            }
        };
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
