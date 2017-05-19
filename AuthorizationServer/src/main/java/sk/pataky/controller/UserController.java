package sk.pataky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sk.pataky.dto.CreateUserDto;
import sk.pataky.dto.UpdateUserDto;
import sk.pataky.dto.UserDto;
import sk.pataky.service.UserService;

import java.util.List;

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

    @PreAuthorize("hasAnyAuthority('ROLE_SYSTEM_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> listUsers() {

        // create this user
        return userService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody CreateUserDto createUserDto) {
        // create this user
        return userService.createUser(createUserDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SYSTEM_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public UserDto updateUser(@PathVariable(name = "id") String id,
                              @RequestBody UpdateUserDto updateUserDto) {

        UserDto userDto = userService.updateUser(id, updateUserDto);
        return userDto;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SYSTEM_ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable(name = "id") String id) {

        userService.deleteUser(id);
    }
}
