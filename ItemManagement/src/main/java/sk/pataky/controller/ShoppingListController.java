package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.dto.shopping.CreateShoppingListDto;
import sk.pataky.service.ShoppingListService;

/**
 *
 */
@RestController()
@RequestMapping("/shoppingLists")
public class ShoppingListController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ShoppingListService shoppingListService;

    @RequestMapping(method = RequestMethod.POST)
    public Long createShoppingList(@RequestBody CreateShoppingListDto createShoppingListDto) {
        return shoppingListService.createShoppingList(createShoppingListDto);
    }
}
