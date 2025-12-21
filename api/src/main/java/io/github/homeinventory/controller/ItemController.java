package io.github.homeinventory.controller;

import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.homeinventory.dto.*;
import io.github.homeinventory.service.ItemService;


@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemResponseDto>> itemList(){
     var result = itemService.getAllItems();
     return ResponseEntity.ok(result);
    }

    @PostMapping("/items")
    public ResponseEntity<String> create(@RequestBody ItemRequestDto req){
        
        itemService.createItem(req);

        return ResponseEntity.status(201).build();

    }

}