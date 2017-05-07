package sk.pataky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.pataky.dto.shopping.CreateShoppingListDto;
import sk.pataky.dto.shopping.ShoppingListDto;
import sk.pataky.dto.shopping.ShoppingListItemDto;
import sk.pataky.model.Item;
import sk.pataky.model.shopping.ShoppingList;
import sk.pataky.model.shopping.ShoppingListEntry;
import sk.pataky.repository.ItemRepository;
import sk.pataky.repository.shopping.ShoppingListRepository;
import sk.pataky.service.ShoppingListService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private ItemRepository itemRepository; // // FIXME: 20/04/2017 use service here or rather change the model!

    @Override
    public String createShoppingList(CreateShoppingListDto createShoppingListDto) {
        ShoppingList shoppingList = new ShoppingList();

        shoppingList.setCreatedOn(new Date());
        List<ShoppingListEntry> shoppingListEntries = shoppingList.getEntires();

        createShoppingListDto.items.forEach(dto -> {
            // TODO: refactor for one select for all items

            Item item = itemRepository.findOne(dto.id);
            // TODO: what if item does not exist? throw not found
            if (item == null) {
                throw new RuntimeException("Item with id " + dto.id + " does not exist.");
            }
            ShoppingListEntry shoppingListEntry = new ShoppingListEntry();
            shoppingListEntry.setItem(item);
            shoppingListEntry.setAddedOn(new Date());
            shoppingListEntry.setQuantity(dto.quantity);

            shoppingListEntries.add(shoppingListEntry);

        });

        shoppingListRepository.save(shoppingList);
        return shoppingList.getId();
    }

    @Override
    public List<ShoppingListDto> findAll() {

        List<ShoppingListDto> dtos = new ArrayList<>();

        shoppingListRepository.findAll().forEach(shoppingList -> {
            ShoppingListDto dto = new ShoppingListDto();
            shoppingList.getEntires().forEach(entry -> {
                ShoppingListItemDto shoppingListItemDto = new ShoppingListItemDto();
                shoppingListItemDto.id = entry.getItem().getId();
                shoppingListItemDto.quantity = entry.getQuantity();
                dto.items.add(shoppingListItemDto);
            });

            dto.id = shoppingList.getId();
            dto.createdOn = shoppingList.getCreatedOn();

            dtos.add(dto);
        });

        return dtos;
    }

    @Override
    public void updateShoppingList(String id, CreateShoppingListDto createShoppingListDto) {

        ShoppingList shoppingList = shoppingListRepository.findOne(id);

        if (shoppingList == null) {
            return; // TODO: 21/04/2017 return not found exception
        }

        List<ShoppingListEntry> shoppingListEntries = shoppingList.getEntires();
        shoppingList.getEntires().clear();

        createShoppingListDto.items.forEach(dto -> {
            // TODO: refactor for one select for all items
            Item item = itemRepository.findOne(dto.id);
            ShoppingListEntry shoppingListEntry = new ShoppingListEntry();
            shoppingListEntry.setItem(item);
            shoppingListEntry.setAddedOn(new Date());
            shoppingListEntry.setQuantity(dto.quantity);

            shoppingListEntries.add(shoppingListEntry);
        });

        shoppingListRepository.save(shoppingList);
    }
}
