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
    public boolean register(Login details) throws Exception {
        if(!details.isValidPwd()) return false;
        if(details.getEmail() == null && details.getUsername() == null) return false;

        var username = details.getUsername() == null ?
                this.getUsernameFromEmail(details.getEmail()) : details.getUsername();

        try {
            this.usersService.register(new UserDto(username, details.getEmail(), null, null), details.getPwd());
        } catch (Exception e) {
            throw new Exception("Ups... something went wrong with your registration.");
        }

        return true;
    }

    /**
     * @param details
     * @return boolean
     */
    @Override
    public boolean login(Login details) throws Exception {
        if((details.getEmail() == null && details.getUsername() == null) || details.getPwd() == null) return false;

        UserDto user = null;

        try {

            if(!details.getEmail().isEmpty()) {
                user = this.usersService.getByEmail(details.getEmail());
                if(user == null) return false;
                return this.usersService.validateCredentialsByEmail(user.getEmail(), details.getPwd());
            } else if(!details.getUsername().isEmpty()) {
                user = this.usersService.getByUsername(details.getUsername());
                if(user == null) return false;
                return this.usersService.validateCredentialsByUsername(user.getUsername(), details.getPwd());
            }

        } catch (Exception e) {
            throw new Exception("Ups... something went wrong with your login.");
        }

        return false;
    }

    private String getUsernameFromEmail(String email) {
        var split = email.split("[@._]");
        return split[0];
    }
}
