package findAnyCat.cat;

import findAnyCat.cat.embbed.Address;
import findAnyCat.cat.exception.NoEnoughStcokException;
import findAnyCat.cat.item.Item;
import findAnyCat.cat.item.associate.Book;
import findAnyCat.cat.item.repository.ItemRepsoitory;
import findAnyCat.cat.member.Member;
import findAnyCat.cat.member.repository.MemberRepository;
import findAnyCat.cat.order.Order;
import findAnyCat.cat.order.repository.OrderRepository;
import findAnyCat.cat.order.service.OrderService;
import findAnyCat.cat.status.OrderStatus;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
public class OrderTest {

    @Autowired private OrderService orderService;
    @Autowired private MemberRepository memberRepository;
    @Autowired private ItemRepsoitory itemRepsoitory;
    @Autowired private OrderRepository orderRepository;

    @Autowired private EntityManager em;

    @Test
    void 주문(){
        Member member = getMember();

        Item item = getItem(itemRepsoitory);

        Long order_id = orderService.save(memberRepository.saveMember(member), item.getId(), 5);

        Assertions.assertEquals(orderRepository.findOne(order_id).getStatus(),OrderStatus.ORDER);
        Assertions.assertEquals(item.getStcokQuantity(),5);
    }

    @Test
    void 상품취소(){
        Member member = getMember();

        Item item = getItem(itemRepsoitory);

        Long order_id = orderService.save(memberRepository.saveMember(member), item.getId(), 5);

        Order order = orderRepository.findOne(order_id);

        order.cancleOrder();

        Assertions.assertEquals(item.getStcokQuantity(),10);
        Assertions.assertEquals(order.getStatus(),OrderStatus.CANCLE);
    }

    @Test
    void 상품초과주문() throws Exception{
        Member member = getMember();

        Item item = getItem(itemRepsoitory);


        Assertions.assertThrows(NoEnoughStcokException.class
                ,()->orderService.save(memberRepository.saveMember(member), item.getId(), 15));
    }

    private static Item getItem(ItemRepsoitory itemRepsoitory) {
        Item book = new Book("김영한의 JPA",50000,10,"김영한","개발");
        itemRepsoitory.addItem(book);
        return book;
    }

    private static Member getMember() {
        Member member = new Member();
        member.setName("김경하");
        member.setAddress(new Address("경기도","시흥시","888"));
        return member;
    }
}
