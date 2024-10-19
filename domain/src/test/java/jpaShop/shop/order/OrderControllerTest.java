package jpaShop.shop.order;

import com.shop.domain.provide.domain.item.Item;
import com.shop.domain.provide.domain.item.associate.Book;
import com.shop.domain.provide.domain.item.controller.request.ItemJoinRequest;
import com.shop.domain.provide.domain.item.exception.NoEnoughStcokException;
import com.shop.domain.provide.domain.item.service.ItemService;
import com.shop.domain.provide.domain.item.service.request.ItemDTO;
import com.shop.domain.provide.domain.member.controller.request.MemberJoinRequest;
import com.shop.domain.provide.domain.member.service.MemberService;
import com.shop.domain.provide.domain.member.service.request.MemberDTO;
import com.shop.domain.provide.domain.order.Order;
import com.shop.domain.provide.domain.order.controller.request.OrderRequest;
import com.shop.domain.provide.domain.order.service.OrderService;
import com.shop.domain.provide.domain.order.service.request.OrderDTO;
import com.shop.domain.provide.domain.order.service.request.OrderSearchRequest;
import com.shop.domain.provide.domain.status.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class OrderControllerTest {

    @Autowired private OrderService orderService;
    @Autowired private MemberService memberService;
    @Autowired private ItemService itemService;

    private Long member_id;
    private Long item_id;
    @BeforeEach
    void init(){
        member_id = getMember(memberService);
        item_id = getItem(itemService);
    }

    @Test
    void 주문(){
        Long order_id = orderService.save(OrderDTO.of(new OrderRequest(member_id,  item_id, 5)));

        assertEquals(orderService.findOrderById(order_id).getStatus(), OrderStatus.ORDER);
    }
    @Test
    void 상품취소(){
        Long order_id = orderService.save(OrderDTO.of(new OrderRequest(member_id,  item_id, 5)));
        Order order = orderService.findOrderById(order_id);

        order.cancleOrder();
        assertEquals(order.getStatus(),OrderStatus.CANCLE);
    }
    @Test
    void 상품초과주문() throws Exception{
        assertThrows(NoEnoughStcokException.class
                ,()->orderService.save(OrderDTO.of(new OrderRequest(member_id, item_id, 15))));
    }
    @Test
    void 상품검색조회(){
        orderService.save(OrderDTO.of(new OrderRequest(member_id,  item_id, 5)));

        List<Order> orderSearchList = orderService.findBySearchOrder(OrderSearchRequest.of("테스터", OrderStatus.ORDER));

        assertEquals(orderSearchList.size(),1);
    }

    private static Long getItem(ItemService itemService) {
        Item book = new Book("김영한의 JPA",50000,10,"테스터","개발");

        ItemJoinRequest itemJoinRequest = new ItemJoinRequest(book.getItemName(), book.getPrice(), book.getStockQuantity(), book.getArtist(), book.getEtc());

        return itemService.saveItem(ItemDTO.of(itemJoinRequest));
    }

    private static Long getMember(MemberService memberService) {
        return memberService.saveMember(
                MemberDTO.of(
                        new MemberJoinRequest("테스터","경기도","시흥시","888")
                ));
    }
}
