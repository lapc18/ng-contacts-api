package io.inab.contacts.infrastructure.services;

import io.inab.contacts.core.interfaces.IAuthService;
import io.inab.contacts.core.models.Login;
import io.inab.contacts.domain.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UsersService usersService;

    /**
     * @param details
     * @return boolean
     */
    @Override
    public UserDto register(Login details) throws Exception {
        if(!details.isValidPwd()) return null;
        if(details.getEmail() == null && details.getUsername() == null) return null;

        var username = details.getUsername() == null ?
                this.getUsernameFromEmail(details.getEmail()) : details.getUsername();

        try {
            return this.usersService.register(new UserDto(username, details.getEmail(), null, null), details.getPwd());
        } catch (Exception e) {
            throw new Exception("Ups... something went wrong with your registration.");
        }

    }

    /**
     * @param details
     * @return boolean
     */
    @Override
    public UserDto login(Login details) throws Exception {
        if((details.getEmail() == null && details.getUsername() == null) || details.getPwd() == null) return null;

        UserDto user = null;
        boolean isValid = false;

        try {

            if(details.getEmail() != null) {
                user = this.usersService.getByEmail(details.getEmail());
                if(user == null) return null;
                isValid = this.usersService.validateCredentialsByEmail(user.getEmail(), details.getPwd());

                if(isValid) return user;
            } else if(details.getUsername() != null) {
                user = this.usersService.getByUsername(details.getUsername());
                if(user == null) return null;

                isValid = this.usersService.validateCredentialsByUsername(user.getUsername(), details.getPwd());

                if(isValid) return user;
            }

        } catch (Exception e) {
            throw new Exception("Ups... something went wrong with your login.");
        }

        return null;
    }

    private String getUsernameFromEmail(String email) {
        var split = email.split("[@._]");
        return split[0];
    }
}
