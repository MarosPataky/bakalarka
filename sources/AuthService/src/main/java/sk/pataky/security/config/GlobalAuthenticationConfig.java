package sk.pataky.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 */
@Configuration
public class GlobalAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
//        auth.inMemoryAuthentication()
//                .withUser("reader")
//                .password("reader")
//                .authorities("FOO_READ")
//                .roles("READER")
//                .and()
//                .withUser("writer")
//                .password("writer")
//                .authorities("FOO_READ", "FOO_WRITE")
//                .roles("READER, WRITER");
    }
}
