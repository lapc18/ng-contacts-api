package io.inab.contacts.core.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class AuthResponse {

    private int id;

    private String username;

    private String email;

    private String token;

}
