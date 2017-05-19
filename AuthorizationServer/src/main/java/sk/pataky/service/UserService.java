package sk.pataky.service;

import sk.pataky.dto.CreateUserDto;
import sk.pataky.dto.UpdateUserDto;
import sk.pataky.dto.UserDto;
import sk.pataky.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();
//    ItemDetailDto getDetail(String id);

    String createUser(CreateUserDto createUserDto);

    User findByEmail(String email);
    User findByUsername(String username);

    UserDto updateUser(String id, UpdateUserDto updateUserDto);

    void deleteUser(String id);


//    void updateItem(String id, CreateItemDto createItemDto);

}
