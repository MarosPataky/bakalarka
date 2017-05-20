package sk.pataky.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 *
 */
@Configuration
public class JwtConfiguration {

    @Value("${security.jwt.public.key}")
    String jwtPublicKey;

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    UserAuthenticationConverter userAuthenticationConverter;

    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore() {
        // FIXME: 09/05/2017 remove this SET
        ((DefaultAccessTokenConverter) jwtAccessTokenConverter.getAccessTokenConverter()).setUserTokenConverter(userAuthenticationConverter);
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter); // FIXME: 09/05/2017 userAuthenticationCOnveter is nul here
        converter.setVerifierKey(jwtPublicKey);
        return converter;
    }

}
