package findAnyCat.cat.item.controller;


import findAnyCat.cat.item.Item;
import findAnyCat.cat.item.controller.request.ItemJoinRequest;
import findAnyCat.cat.item.service.ItemService;
import findAnyCat.cat.item.service.dto.ItemDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @RequestMapping("/items/new")
    public String addItemPage(Model model){
        model.addAttribute("itemJoinRequest",ItemJoinRequest.of("",0,0,"",""));
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

        ItemDTO itemDTO = ItemDTO.of(
                itemJoinRequest.name()
                ,itemJoinRequest.price()
                ,itemJoinRequest.stockQuantity()
                ,itemJoinRequest.author()
                ,itemJoinRequest.isbn()
        );
        itemService.saveItemToBuilder(itemDTO);
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
            @PathVariable("id")Long id
            ,@Valid ItemJoinRequest itemJoinRequest
            ,BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return "redirect:/items/" + id + "/edit";
        }

        itemService.findItem(id);


        return "redirect:/";
    }

}
