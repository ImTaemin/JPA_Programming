package org.tmkim.jpashop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tmkim.jpashop.domain.item.Book;
import org.tmkim.jpashop.domain.item.Item;
import org.tmkim.jpashop.service.ItemService;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController
{
    @Autowired
    ItemService itemService;

    @GetMapping("/new")
    public String createForm()
    {
        return "items/createItemForm";
    }

    @PostMapping("/new")
    public String create(Book item)
    {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("items")
    public String list(Model model)
    {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    //상품 수정 폼
    @GetMapping("{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model)
    {
        Item item = itemService.findOne(itemId);
        model.addAttribute("item", item);
        return "items/updateItemForm";
    }
    
    //상품 수정
    @PostMapping("{itemId}/edit")
    public String updateItem(@ModelAttribute("item") Book item)
    {
        itemService.saveItem(item);
        return "redirect:/items";
    }
}
