package sk.pataky.service;

import sk.pataky.dto.CreateUserDto;
import sk.pataky.model.User;

public interface UserService {

//    List<ItemDto> getAll();
//    ItemDetailDto getDetail(String id);

    String createUser(CreateUserDto createUserDto);

    User findByEmail(String email);
    User findByUsername(String username);


//    void updateItem(String id, CreateItemDto createItemDto);

}
