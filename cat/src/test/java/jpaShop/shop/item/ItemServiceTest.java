package jpaShop.shop.item;

import jpaShop.shop.domain.item.Item;
import jpaShop.shop.domain.item.associate.Book;
import jpaShop.shop.domain.item.repository.ItemRepsoitory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired private ItemRepsoitory itemRepsoitory;

    @Test
    void 아이템등록(){
        Item item = Book.bookBuilder()
                .itemName("테스트아이템")
                .price(3000)
                .stockQuantity(10)
                .build();
        Item save = itemRepsoitory.save(item);

        assertThat(item).isEqualTo(save);
    }

}
