package jpaShop.shop.domain.item.controller;


import jpaShop.shop.domain.item.controller.request.ItemJoinRequest;
import jpaShop.shop.domain.item.service.ItemService;
import jpaShop.shop.domain.item.service.request.ItemDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @RequestMapping("/items/new")
    public String addItemPage(Model model, ItemJoinRequest itemJoinRequest){
        model.addAttribute("itemJoinRequest",itemJoinRequest);
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String addItem(
            @Valid ItemJoinRequest itemJoinRequest,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()) {
            return "items/createItemForm";
        }

        itemService.saveItem(ItemDTO.of(itemJoinRequest));
        return "redirect:/";
    }

    @GetMapping("/items")
    public String getItems(Model model){
        model.addAttribute("items",itemService.findAllItem());
        return "items/itemList";
    }

    @GetMapping("/items/{id}/edit")
    public String updatePage(@PathVariable("id") Long id,Model model){

        model.addAttribute("itemJoinRequest",itemService.findItem(id));
        return "items/updateItemForm";
    }

    @PostMapping("/items/{id}/edit")
    public String updateItem(
            @PathVariable("id")Long itemId
            ,@Valid ItemJoinRequest itemJoinRequest
            ,BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return "redirect:/items/" + itemId + "/edit";
        }

        itemService.updateItem(itemId,itemJoinRequest);

        return "redirect:/";
    }

}
