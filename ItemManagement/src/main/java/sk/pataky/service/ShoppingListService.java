package sk.pataky.service;

import sk.pataky.dto.shopping.CreateShoppingListDto;
import sk.pataky.dto.shopping.ShoppingListDto;

import java.util.List;

/**
 *
 */
public interface ShoppingListService {
    Long createShoppingList(CreateShoppingListDto createShoppingListDto);

    List<ShoppingListDto> findAll();

    void updateShoppingList(Long id, CreateShoppingListDto createShoppingListDto);
}
