package findAnyCat.cat.item.controller;


import findAnyCat.cat.item.Item;
import findAnyCat.cat.item.controller.request.ItemJoinRequest;
import findAnyCat.cat.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @RequestMapping("/items/new")
    public String addItemPage(Model model){
        model.addAttribute("form",new ItemJoinRequest("",0,0,"",""));
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String addItem(ItemJoinRequest itemJoinRequest){
        itemService.saveItemToBuilder(itemJoinRequest);
        return "redirect:/";
    }

}
