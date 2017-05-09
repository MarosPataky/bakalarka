package sk.pataky.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.pataky.dto.CreateUserDto;
import sk.pataky.model.User;
import sk.pataky.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setUsername(createUserDto.username);
        user.setPassword(createUserDto.password);
        user.setEmail(createUserDto.email);

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
}
