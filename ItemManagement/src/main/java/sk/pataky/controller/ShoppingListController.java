package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.dto.shopping.CreateShoppingListDto;
import sk.pataky.dto.shopping.ShoppingListDto;
import sk.pataky.service.ShoppingListService;

import java.util.List;

/**
 *
 */
@RestController()
@RequestMapping("/shoppingLists")
public class ShoppingListController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListController.class);

    @Autowired
    private ShoppingListService shoppingListService;

    @RequestMapping(method = RequestMethod.POST)
    public String createShoppingList(@RequestBody CreateShoppingListDto createShoppingListDto) {
        return shoppingListService.createShoppingList(createShoppingListDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ShoppingListDto> findAll() {
        return shoppingListService.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public void updateShoppingList(@PathVariable() String id,
                                                    @RequestBody CreateShoppingListDto createShoppingListDto) {
        // TODO: 21/04/2017 replace with updateShoppingListDto + add PATCH operation (optional)
        shoppingListService.updateShoppingList(id, createShoppingListDto);
    }
}
