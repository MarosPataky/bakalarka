package sk.pataky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.pataky.dto.shopping.CreateShoppingListDto;
import sk.pataky.model.Item;
import sk.pataky.model.shopping.ShoppingList;
import sk.pataky.model.shopping.ShoppingListEntry;
import sk.pataky.repository.ItemRepository;
import sk.pataky.repository.shopping.ShoppingListRepository;
import sk.pataky.service.ItemService;
import sk.pataky.service.ShoppingListService;

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
    public Long createShoppingList(CreateShoppingListDto createShoppingListDto) {
        ShoppingList shoppingList = new ShoppingList();

        shoppingList.setCreatedOn(new Date());
        List<ShoppingListEntry> shoppingListEntries = shoppingList.getItems();

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
        return shoppingList.getId();
    }
}
