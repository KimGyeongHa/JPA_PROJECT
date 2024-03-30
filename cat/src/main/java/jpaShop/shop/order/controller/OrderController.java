package jpaShop.shop.order.controller;

import jpaShop.shop.item.service.ItemService;
import jpaShop.shop.member.service.MemberService;
import jpaShop.shop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;
    private final MemberService memberService;

    @GetMapping("/order")
    public String orderPage(Model model){
        model.addAttribute("members",memberService.findMembers());
        model.addAttribute("items",itemService.findAllItem());
        return "order/orderForm";
    }


}
