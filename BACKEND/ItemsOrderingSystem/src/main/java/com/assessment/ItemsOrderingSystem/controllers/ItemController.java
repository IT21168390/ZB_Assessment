package com.assessment.ItemsOrderingSystem.controllers;

import com.assessment.ItemsOrderingSystem.models.Items;
import com.assessment.ItemsOrderingSystem.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<?> getAllItemsAvailable() {
        List<Items> itemsDTOList = itemService.getAllItems();
        System.out.println(itemsDTOList);
        return new ResponseEntity<>(itemsDTOList, HttpStatus.OK);
    }
}
