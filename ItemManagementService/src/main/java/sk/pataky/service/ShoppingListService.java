package sk.pataky.service;

import sk.pataky.dto.shopping.CreateShoppingListDto;
import sk.pataky.dto.shopping.ShoppingListDto;

import java.util.List;

/**
 *
 */
public interface ShoppingListService {
    String createShoppingList(CreateShoppingListDto createShoppingListDto, String userId);

    List<ShoppingListDto> findAll();

    void updateShoppingList(String id, CreateShoppingListDto createShoppingListDto, String userId);

    List<ShoppingListDto> findAllForUser(String userId);

    void deleteShoppingList(String id, String userId);
}
