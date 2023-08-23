package com.assessment.ItemsOrderingSystem.services;

import com.assessment.ItemsOrderingSystem.dto.ItemsDTO;
import com.assessment.ItemsOrderingSystem.models.Items;
import com.assessment.ItemsOrderingSystem.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Items> getAllItems() {
        return itemRepository.findAll();
    }

    public ItemsDTO getItem(int itemId) {
        Items items = itemRepository.findByItemId(itemId);
        ItemsDTO itemsDTO = new ItemsDTO();
        itemsDTO.setItemId(items.getItemId());
        itemsDTO.setItemName(items.getItemName());
        itemsDTO.setPrice(items.getPrice());
        return itemsDTO;
    }
}
