package sk.pataky.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Document(collection = "users")
public class User extends BaseEntity {
    @Indexed(unique = true)
    private String username;

    private String password;

    @Indexed(unique = true)
    private String email;

    private List<UserRoles> roles;
    //TODO: user profile
//    private UserProfi UserProfile;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRoles> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }

    public void setRoles(List<UserRoles> roles) {
        this.roles = roles;
    }

    public enum UserRoles {
        CUSTOMER("ROLE_CUSTOMER"),
        MERCHANT("ROLE_MERCHANT");

        private final String ROLE;

        UserRoles(String role) {
            this.ROLE = role;
        }

        public String value() {
            return this.ROLE;
        }
    }
}
