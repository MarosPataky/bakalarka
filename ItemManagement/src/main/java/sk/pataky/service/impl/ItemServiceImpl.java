package sk.pataky.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.pataky.dto.CreateItemDto;
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
            itemDto.name = item.getName();
            itemDto.itemQuantityUnit = item.getItemQuantityUnit();
            itemDto.amount = item.getAmount();
            itemDto.brand = item.getBrand();
            itemDto.id = item.getId();

            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    @Override
    public ItemDetailDto getDetail(String id) {

        Item item = itemRepository.findOne(id);

        if (item == null) {
            // TODO: return NotFoundException!
            return null;
        }

        ItemDetailDto itemDetailDto = new ItemDetailDto();
//        itemDetailDto.prices = item.getPrices();
        itemDetailDto.name = item.getName();
        itemDetailDto.description = item.getDescription();
        itemDetailDto.itemQuantityUnit = item.getItemQuantityUnit();
        itemDetailDto.amount = item.getAmount();
        itemDetailDto.id = item.getId();


        itemDetailDto.prices = new HashMap<>();
        // tetsing only
        for (Price price : item.getPrices()) {
            itemDetailDto.prices.put(price.getShop(), price.getPrice());
        }
        return itemDetailDto;
    }

    @Override
    public String createItem(CreateItemDto createItemDto) {

        Item item = new Item();
        item.setName(createItemDto.name);
        item.setBrand(createItemDto.brand);
        item.setDescription(createItemDto.description);
        item.setAmount(createItemDto.amount);
        item.setItemQuantityUnit(createItemDto.itemQuantityUnit);

        createItemDto.prices.forEach( (shop, price) -> {
            Price priceEntity = new Price();
            priceEntity.setPrice(price);
            priceEntity.setShop(shop);
            item.getPrices().add(priceEntity);
        });

        itemRepository.save(item);
        return item.getId();
    }

    @Override
    public void updateItem(String id, CreateItemDto createItemDto) {
        Item item = itemRepository.findOne(id);

        if (item == null) {
            // TODO: throw NotFoundException!
            return;
        }

        item.setName(createItemDto.name);
        item.setBrand(createItemDto.brand);
        item.setDescription(createItemDto.description);
        item.setAmount(createItemDto.amount);
        item.setItemQuantityUnit(createItemDto.itemQuantityUnit);

        item.getPrices().clear();
        createItemDto.prices.forEach( (shop, price) -> {
            Price priceEntity = new Price();
            priceEntity.setPrice(price);
            priceEntity.setShop(shop);
            item.getPrices().add(priceEntity);
        });

        itemRepository.save(item);
    }
}
