package com.board.My.Board.service;

import com.board.My.Board.domain.Category;
import com.board.My.Board.domain.Item;
import com.board.My.Board.repository.ItemRepository;
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

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public List<Item>  findCategory(Category category){
        return itemRepository.findCategory(category);
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public Long join(Item item) {
        itemRepository.save(item);
        return item.getId();
    }
}
