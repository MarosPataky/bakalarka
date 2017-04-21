package sk.pataky.service;

import sk.pataky.dto.shopping.CreateShoppingListDto;

/**
 *
 */
public interface ShoppingListService {
    Long createShoppingList(CreateShoppingListDto createShoppingListDto);
}
