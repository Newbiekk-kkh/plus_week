package com.example.demo.domain.item.service;

import com.example.demo.domain.item.entity.Item;
import com.example.demo.domain.item.repository.ItemRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemService(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createItem(String name, String description, Long ownerId, Long managerId) {
        User owner = userRepository.findByIdOrElseThrow(ownerId);
        User manager = userRepository.findByIdOrElseThrow(managerId);

        Item item = new Item(name, description, owner, manager);
        itemRepository.save(item);
    }
}
