package org.tmkim.jpashop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tmkim.jpashop.domain.Member;
import org.tmkim.jpashop.domain.item.Item;
import org.tmkim.jpashop.exception.NotEnoughStockException;
import org.tmkim.jpashop.service.ItemService;
import org.tmkim.jpashop.service.MemberService;
import org.tmkim.jpashop.service.OrderService;

import java.util.List;

@Controller
public class OrderController
{
    @Autowired
    OrderService orderService;

    @Autowired
    MemberService memberService;

    @Autowired
    ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model)
    {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(
            @RequestParam("memberId") Long memberId,
            @RequestParam("itemId") Long itemId,
            @RequestParam("count") int count) throws NotEnoughStockException
    {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }
}
