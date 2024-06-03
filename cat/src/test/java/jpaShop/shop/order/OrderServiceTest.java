package jpaShop.shop.order;

import jpaShop.shop.domain.item.Item;
import jpaShop.shop.domain.item.associate.Book;
import jpaShop.shop.domain.item.controller.request.ItemJoinRequest;
import jpaShop.shop.domain.item.exception.NoEnoughStcokException;
import jpaShop.shop.domain.item.repository.ItemRepsoitory;
import jpaShop.shop.domain.item.service.ItemService;
import jpaShop.shop.domain.item.service.request.ItemDTO;
import jpaShop.shop.domain.member.controller.request.MemberJoinRequest;
import jpaShop.shop.domain.member.service.MemberService;
import jpaShop.shop.domain.member.service.request.MemberDTO;
import jpaShop.shop.domain.order.Order;
import jpaShop.shop.domain.order.controller.request.OrderRequest;
import jpaShop.shop.domain.order.exception.NotFoundOrderException;
import jpaShop.shop.domain.order.repository.OrderRepository;
import jpaShop.shop.domain.order.service.OrderService;
import jpaShop.shop.domain.order.service.request.OrderDTO;
import jpaShop.shop.domain.order.service.request.OrderSearchRequest;
import jpaShop.shop.domain.status.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired private OrderService orderService;
    @Autowired private MemberService memberService;
    @Autowired private ItemService itemService;
    @Test
    void 주문(){
        MemberDTO memberDTO = getMember();

        Item item = getItem(itemService);

        Long order_id = orderService.save(OrderDTO.of(new OrderRequest(memberService.saveMember(memberDTO), item.getId(), 5)));

        Assertions.assertEquals(orderService.findOrderById(order_id).getStatus(), OrderStatus.ORDER);
        Assertions.assertEquals(item.getStockQuantity(),5);
    }
    @Test
    void 상품취소(){
        MemberDTO memberDTO = getMember();

        Item item = getItem(itemService);

        Long order_id = orderService.save(OrderDTO.of(new OrderRequest(memberService.saveMember(memberDTO), item.getId(), 5)));

        Order order = orderService.findOrderById(order_id);

        order.cancleOrder();

        Assertions.assertEquals(item.getStockQuantity(),10);
        Assertions.assertEquals(order.getStatus(),OrderStatus.CANCLE);
    }
    @Test
    void 상품초과주문() throws Exception{
        MemberDTO memberDTO = getMember();

        Item item = getItem(itemService);

        Assertions.assertThrows(NoEnoughStcokException.class
                ,()->orderService.save(OrderDTO.of(new OrderRequest(memberService.saveMember(memberDTO), item.getId(), 15))));
    }
    @Test
    void 상품검색조회(){
        Item item = getItem(itemService);
        MemberDTO memberDTO = getMember();

        Long order_id = orderService.save(OrderDTO.of(new OrderRequest(memberService.saveMember(memberDTO), item.getId(), 5)));

        List<Order> orderSearchList = orderService.findBySearchOrder(OrderSearchRequest.of("김영한", OrderStatus.ORDER));

        org.assertj.core.api.Assertions.assertThat(orderSearchList.size()).isEqualTo(1);
    }

    private static Item getItem(ItemService itemService) {
        Item book = new Book("김영한의 JPA",50000,10,"김영한","개발");

        ItemJoinRequest itemJoinRequest = new ItemJoinRequest(book.getItemName(), book.getPrice(), book.getStockQuantity(), book.getArtist(), book.getEtc());
        itemService.saveItem(ItemDTO.of(itemJoinRequest));

        return book;
    }

    private static MemberDTO getMember() {
        return MemberDTO.of(new MemberJoinRequest("김경하","경기도","시흥시","888"));
    }
}
