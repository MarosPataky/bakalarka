package sk.pataky.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.pataky.model.User;
import sk.pataky.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    // this is used when checking if user exists .. the returned object is used for password check
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username);

        // TODO: load real user
//        if not leaded, throw exception
        if (user == null) {
            throw new UsernameNotFoundException(
                    "No user found with username: "+ username);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        String[] roles = appUser.getRoles().split(",");
        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> {
            roles.add(role.value());
        });

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        String id = user.getId();
        UserDetailsImpl userDetails =
                new UserDetailsImpl(
                        id,
                        user.getUsername(),
                        user.getPassword(),
                        true, true, true, true,
                        authorities
                );
        return userDetails;
    }


}
