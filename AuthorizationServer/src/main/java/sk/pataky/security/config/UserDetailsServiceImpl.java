package sk.pataky.security.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // this is used when checking if user exists .. the returned object is used for password check
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // TODO: load real user
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        String[] roles = appUser.getRoles().split(",");
        String[] roles = {"FOO_READ", "FOO_WRITE", "THIRD_ROLE"};
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        String id = "id-1";
        UserDetailsImpl userDetails =
                new UserDetailsImpl(
                        id,
                        "name",
                        "password",
                        true, true, true, true,
                        authorities
                );
        return userDetails;
    }
}
