package sk.pataky.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.pataky.dto.CreateUserDto;
import sk.pataky.dto.UpdateUserDto;
import sk.pataky.dto.UserDto;
import sk.pataky.model.User;
import sk.pataky.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();

        return users.stream().map(this::toUserDto).collect(Collectors.toList());
    }

    @Override
    public String createUser(CreateUserDto createUserDto) {
        if (createUserDto.email == null || createUserDto.username == null || createUserDto.password == null) {
            return null;
        }
        User user = new User();
        user.setUsername(createUserDto.username);
        user.setPassword(createUserDto.password);
        user.setEmail(createUserDto.email);


        List<User.UserRole> roles = new ArrayList<>();
        roles.add(User.UserRole.CUSTOMER);
        user.setRoles(roles);

        userRepository.save(user);

        return user.getId();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDto updateUser(String id, UpdateUserDto updateUserDto) {
        User user = userRepository.findOne(id);

        if (user == null) {
            // TODO: 19/05/2017 return 404 response
            return null;
        }

        if (updateUserDto.email != null) {
            user.setEmail(updateUserDto.email);
        }
        if (updateUserDto.password != null) {
            user.setPassword(updateUserDto.password);
        }
        if (updateUserDto.username != null) {
            user.setUsername(updateUserDto.username);
        }
        if (updateUserDto.roles != null) {

            List<User.UserRole> roles = new ArrayList<>();

            updateUserDto.roles.forEach(role -> {
                roles.add(User.UserRole.fromString(role));
            });

            user.setRoles(roles);
        }

        if (updateUserDto.email != null) {
            user.setEmail(updateUserDto.email);
        }

        userRepository.save(user);


        return toUserDto(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.delete(id);
    }

    private UserDto toUserDto(User user) {
        UserDto dto = new UserDto();
        dto.username = user.getUsername();
        dto.id = user.getId();
        dto.email = user.getEmail();

        dto.roles = new ArrayList<String>();
        // convert roles to string
        user.getRoles().forEach(role -> {
            dto.roles.add(role.value());
        });
        return dto;
    }
}
