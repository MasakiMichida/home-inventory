package io.github.homeinventory.service;

import org.springframework.stereotype.Service;
import java.util.*;
import io.github.homeinventory.dto.*;
import io.github.homeinventory.repository.ItemRepository;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    public List<ItemResponseDto> getAllItems() {
        var result = itemRepository.findAll();
        return result;
    }

    public int createItem(ItemRequestDto req) {

        if (req.quantity() < 0) {
            throw new IllegalArgumentException("数量に0未満が入力されています");
        } else if (req.name() == null || req.name().isBlank()) {
            throw new IllegalArgumentException("名称が未入力です");
        }

        return itemRepository.insert(req);
    }
}
