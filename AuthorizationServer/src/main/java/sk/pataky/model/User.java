package sk.pataky.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Document(collection = "users")
public class User extends BaseEntity {
    @Indexed(unique = true)
    @NotNull(message = "Username cannot be empty or null")
    private String username;

    @NotNull(message = "Password cannot be empty or null")
    private String password;

    @Indexed(unique = true)
    @NotNull(message = "Email cannot be empty or null")
    private String email;

    @NotEmpty
    private List<UserRole> roles;
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

    public List<UserRole> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public enum UserRole {
        CUSTOMER("ROLE_CUSTOMER"),
        MERCHANT("ROLE_MERCHANT"),
        SYSTEM_ADMIN("ROLE_SYSTEM_ADMIN");

        private final String ROLE;

        UserRole(String role) {
            this.ROLE = role;
        }

        public String value() {
            return this.ROLE;
        }

        public static UserRole fromString(String string) {
            for (UserRole userRole : values()) {
                if (userRole.value().equalsIgnoreCase(string)) {
                    return userRole;
                }
            }
            return null;
        }
    }
}
