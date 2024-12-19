package com.example.demo.domain.item.controller;

import com.example.demo.domain.item.dto.ItemRequestDto;
import com.example.demo.domain.item.service.ItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public void createItem(@RequestBody ItemRequestDto itemRequestDto) {
         itemService.createItem(itemRequestDto.getName(),
                                itemRequestDto.getDescription(),
                                itemRequestDto.getOwnerId(),
                                itemRequestDto.getManagerId());
    }
}
