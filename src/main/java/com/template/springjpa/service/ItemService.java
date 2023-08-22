package com.template.springjpa.service;

import com.template.springjpa.domain.item.Book;
import com.template.springjpa.domain.item.Item;
import com.template.springjpa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Long itemId, Book param){
        Item item = itemRepository.findOne(itemId);

        //Entity 메소드를 작성하여 변경 적용하는 것이 바람직
        item.setName(param.getName());
        item.setPrice(param.getPrice());
        item.setStockQuantity(param.getStockQuantity());

        return item;
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }
}
