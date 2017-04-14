package sk.pataky.service;

import sk.pataky.dto.ItemDetailDto;
import sk.pataky.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> getAll();
    ItemDetailDto getDetail(Long id);
}
