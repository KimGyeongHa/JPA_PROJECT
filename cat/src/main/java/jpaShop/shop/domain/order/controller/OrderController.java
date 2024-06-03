package jpaShop.shop.domain.order.controller;

import jakarta.validation.Valid;
import jpaShop.shop.domain.item.service.ItemService;
import jpaShop.shop.domain.member.service.MemberService;
import jpaShop.shop.domain.order.controller.request.OrderRequest;
import jpaShop.shop.domain.order.service.OrderService;
import jpaShop.shop.domain.order.service.request.OrderDTO;
import jpaShop.shop.domain.order.service.request.OrderSearchRequest;
import jpaShop.shop.domain.status.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final OrderService orderService;
    @GetMapping("/order")
    public String orderPage(Model model,OrderRequest orderRequest){
        model.addAttribute("members",memberService.findAllMembers());
        model.addAttribute("items",itemService.findAllItem());
        model.addAttribute("orderRequest", orderRequest);
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(
            @Valid OrderRequest orderRequest,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("members",memberService.findAllMembers());
            model.addAttribute("items",itemService.findAllItem());
            return "order/orderForm";
        }

        orderService.save(OrderDTO.of(orderRequest));

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderListPage(
            Model model,
            OrderSearchRequest orderSearchRequest
    ){

        model.addAttribute("orders",orderService.findBySearchOrder(orderSearchRequest));
        model.addAttribute("orderSearch", OrderSearchRequest.of("", OrderStatus.ORDER));
        return "order/orderList";
    }


    @PostMapping("/orders/{id}/cancel")
    public String orderCancle(@PathVariable(value = "id") Long order_id) {

        orderService.orderCancle(order_id);
        return "redirect:/orders";
    }

}
