package sk.pataky.dto;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class CreateUserDto {

    @NotNull
    public String username;
    @NotNull
    public String password;
    @NotNull
    public String email;

}
