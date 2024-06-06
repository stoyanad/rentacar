package org.example.rc.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {
    @NotNull
    private String username;

    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    /**
     * Partial constructor
     */
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
