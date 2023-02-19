package io.inab.contacts.core.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class AuthResponse {

    private String username;

    private String token;

}
