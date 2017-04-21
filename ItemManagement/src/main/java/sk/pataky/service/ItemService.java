package sk.pataky.service;

import sk.pataky.dto.CreateItemDto;
import sk.pataky.dto.ItemDetailDto;
import sk.pataky.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> getAll();
    ItemDetailDto getDetail(String id);

    String createItem(CreateItemDto createItemDto);

    void updateItem(String id, CreateItemDto createItemDto);
}
