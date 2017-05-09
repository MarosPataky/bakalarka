package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.dto.CreateUserDto;
import sk.pataky.service.UserService;

/**
 *
 */
@RestController()
@RequestMapping("/users")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


//    @RequestMapping(method = RequestMethod.GET)
//    public List<ItemDto> getAll(@RequestParam(value = "brand", required = false) String brand) {
//        if (brand != null) {
//            LOGGER.info("GetALL request with brand={}", brand);
//            return itemService.getAll()
//                    .parallelStream()
//                    .filter(item -> item.brand.equalsIgnoreCase(brand))
//                    .collect(Collectors.toList());
//        }
//        LOGGER.info("GetAll request received!");
//        return itemService.getAll();
//    }

//    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
//    public ItemDetailDto getDetail(@PathVariable("id") String id) {
//        return itemService.getDetail(id);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
//    public void updateItem(@PathVariable("id") String id,
//                           @RequestBody CreateItemDto createItemDto) {
//        itemService.updateItem(id, createItemDto);
//        // todo: return some meaningful response
//    }
//
    @RequestMapping(method = RequestMethod.POST)
    public String createItem(@RequestBody CreateUserDto createUserDto) {

        // create this user
        return userService.createUser(createUserDto);
    }
}
