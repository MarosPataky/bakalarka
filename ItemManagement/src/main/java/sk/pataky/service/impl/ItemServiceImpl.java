package sk.pataky.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.pataky.dto.ItemDetailDto;
import sk.pataky.dto.ItemDto;
import sk.pataky.model.Item;
import sk.pataky.model.Price;
import sk.pataky.repository.ItemRepository;
import sk.pataky.service.ItemService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ItemDto> getAll() {

        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : itemRepository.findAll()) {
            ItemDto itemDto = new ItemDto();
//            itemDto.description = item.getDescription();
            itemDto.lowestPrice = item.getPrices().get(0).getPrice(); // todo: null pointer
            itemDto.title = item.getName();
            itemDto.itemQuantityUnit = item.getItemQuantityUnit();
            itemDto.amount = item.getAmount();
            itemDto.brand = item.getBrand();

            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    @Override
    public ItemDetailDto getDetail(Long id) {

        Item item = itemRepository.findOne(id);

        if (item == null) {
            return null;
        }

        ItemDetailDto itemDetailDto = new ItemDetailDto();
        itemDetailDto.prices = item.getPrices();
        itemDetailDto.title = item.getName();
        itemDetailDto.description = item.getDescription();
        itemDetailDto.itemQuantityUnit = item.getItemQuantityUnit();
        itemDetailDto.amount = item.getAmount();


        itemDetailDto.shopPriceMap = new HashMap<>();
        // tetsing only
        for (Price price : item.getPrices()) {
            itemDetailDto.shopPriceMap.put(price.getShop(), price.getPrice());
        }
        return itemDetailDto;
    }
}
