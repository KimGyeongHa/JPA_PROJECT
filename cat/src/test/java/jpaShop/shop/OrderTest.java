package jpaShop.shop;

import jpaShop.shop.domain.item.Item;
import jpaShop.shop.domain.item.associate.Book;
import jpaShop.shop.domain.item.exception.NoEnoughStcokException;
import jpaShop.shop.domain.item.repository.ItemRepsoitory;
import jpaShop.shop.domain.member.controller.request.MemberJoinRequest;
import jpaShop.shop.domain.member.repository.MemberRepository;
import jpaShop.shop.domain.member.service.request.MemberDTO;
import jpaShop.shop.domain.order.Order;
import jpaShop.shop.domain.order.controller.request.OrderRequest;
import jpaShop.shop.domain.order.repository.OrderRepository;
import jpaShop.shop.domain.order.service.OrderService;
import jpaShop.shop.domain.order.service.request.OrderDTO;
import jpaShop.shop.domain.status.OrderStatus;
import jakarta.persistence.EntityManager;
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
        MemberDTO memberDTO = getMember();

        Item item = getItem(itemRepsoitory);

        Long order_id = orderService.save(OrderDTO.of(new OrderRequest(memberRepository.saveMember(memberDTO), item.getId(), 5)));

        Assertions.assertEquals(orderRepository.findOne(order_id).getStatus(), OrderStatus.ORDER);
        Assertions.assertEquals(item.getStockQuantity(),5);
    }

    @Test
    void 상품취소(){
        MemberDTO memberDTO = getMember();

        Item item = getItem(itemRepsoitory);

        Long order_id = orderService.save(OrderDTO.of(new OrderRequest(memberRepository.saveMember(memberDTO), item.getId(), 5)));

        Order order = orderRepository.findOne(order_id);

        order.cancleOrder();

        Assertions.assertEquals(item.getStockQuantity(),10);
        Assertions.assertEquals(order.getStatus(),OrderStatus.CANCLE);
    }

    @Test
    void 상품초과주문() throws Exception{
        MemberDTO memberDTO = getMember();

        Item item = getItem(itemRepsoitory);

        Assertions.assertThrows(NoEnoughStcokException.class
                ,()->orderService.save(OrderDTO.of(new OrderRequest(memberRepository.saveMember(memberDTO), item.getId(), 15))));
    }

    private static Item getItem(ItemRepsoitory itemRepsoitory) {
        Item book = new Book("김영한의 JPA",50000,10,"김영한","개발");
        itemRepsoitory.addItem(book);
        return book;
    }

    private static MemberDTO getMember() {
        return MemberDTO.of(new MemberJoinRequest("김경하","경기도","시흥시","888"));
    }
}
