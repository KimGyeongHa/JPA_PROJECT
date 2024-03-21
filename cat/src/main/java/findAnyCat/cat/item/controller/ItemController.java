package findAnyCat.cat.item.controller;


import findAnyCat.cat.item.controller.request.ItemJoinRequest;
import findAnyCat.cat.item.service.ItemService;
import findAnyCat.cat.item.service.dto.ItemDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
