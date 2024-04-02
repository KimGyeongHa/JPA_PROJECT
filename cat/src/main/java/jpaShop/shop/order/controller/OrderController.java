package jpaShop.shop.order.controller;

import jakarta.validation.Valid;
import jpaShop.shop.item.Item;
import jpaShop.shop.item.service.ItemService;
import jpaShop.shop.member.Member;
import jpaShop.shop.member.service.MemberService;
import jpaShop.shop.order.Order;
import jpaShop.shop.order.controller.request.OrderRequest;
import jpaShop.shop.order.controller.request.OrderSearchRequest;
import jpaShop.shop.order.service.OrderService;
import jpaShop.shop.status.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final OrderService orderService;
    @GetMapping("/order")
    public String orderPage(Model model){

        model.addAttribute("members",memberService.findMembers());
        model.addAttribute("items",itemService.findAllItem());
        model.addAttribute("orderRequest", OrderRequest.of(0L,0L,0));
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(
            @Valid OrderRequest orderRequest,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("members",memberService.findMembers());
            model.addAttribute("items",itemService.findAllItem());
            return "order/orderForm";
        }

        orderService.save(
                orderRequest.memberId()
                ,orderRequest.itemId()
                ,orderRequest.orderStockQuantity()
        );

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderListPage(
            Model model,
            OrderSearchRequest orderSearchRequest
    ){

        model.addAttribute("orders",orderService.getOrderList(orderSearchRequest));
        model.addAttribute("orderSearch", OrderSearchRequest.of("", OrderStatus.ORDER));
        return "order/orderList";
    }


    @PostMapping("/orders/{id}/cancel")
    public String orderCancle(@PathVariable(value = "id") Long order_id) {

        orderService.orderCancle(order_id);
        return "redirect:/orders";
    }

}
