package org.tmkim.jpashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tmkim.jpashop.domain.item.Item;
import org.tmkim.jpashop.repository.ItemRepository;

import java.util.List;

@Service
@Transactional
public class ItemService
{
    @Autowired
    ItemRepository itemRepository;

    public void saveItem(Item item)
    {
        itemRepository.save(item);
    }

    public List<Item> findItems()
    {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId)
    {
        return itemRepository.findOne(itemId);
    }
}
