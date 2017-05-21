package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.dto.shopping.CreateShoppingListDto;
import sk.pataky.dto.shopping.ShoppingListDto;
import sk.pataky.security.UserDetailsImpl;
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

    @PreAuthorize("hasAnyRole('CUSTOMER', 'SYSTEM_ADMIN', 'MERCHANT')")
    @RequestMapping(method = RequestMethod.POST)
    public String createShoppingList(@RequestBody CreateShoppingListDto createShoppingListDto,
                                     Authentication authentication) {
        String userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        return shoppingListService.createShoppingList(createShoppingListDto, userId);
    }

    @PreAuthorize("hasAnyRole('CUSTOMER', 'SYSTEM_ADMIN', 'MERCHANT')")
    @RequestMapping(method = RequestMethod.GET)
    public List<ShoppingListDto> findAll(Authentication authentication) {
        String userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        LOGGER.info("Searching for shopping lists for user with id {}", userId);
        // TODO: 19/05/2017 creted by based on spring securitycontext principal
        return shoppingListService.findAllForUser(userId);
    }

    @PreAuthorize("hasAnyRole('CUSTOMER', 'SYSTEM_ADMIN', 'MERCHANT')")
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public void updateShoppingList(@PathVariable() String id,
                                   @RequestBody CreateShoppingListDto createShoppingListDto,
                                   Authentication authentication) {
        String userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        // TODO: 21/04/2017 replace with updateShoppingListDto + add PATCH operation (optional)
        shoppingListService.updateShoppingList(id, createShoppingListDto, userId);
    }

    @PreAuthorize("hasAnyRole('CUSTOMER', 'SYSTEM_ADMIN', 'MERCHANT')")
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void deleteShoppingList(@PathVariable() String id,
                                   Authentication authentication) {
        String userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        // TODO: 21/04/2017 replace with updateShoppingListDto + add PATCH operation (optional)
        shoppingListService.deleteShoppingList(id, userId);
    }
}
