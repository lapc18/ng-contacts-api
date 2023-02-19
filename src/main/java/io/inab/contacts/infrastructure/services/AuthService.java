package io.inab.contacts.infrastructure.services;

import io.inab.contacts.configs.jwt.JwtUtil;
import io.inab.contacts.core.exceptions.UserNotFoundException;
import io.inab.contacts.core.interfaces.IAuthService;
import io.inab.contacts.core.models.AuthResponse;
import io.inab.contacts.core.models.Login;
import io.inab.contacts.domain.dtos.UserDto;
import io.inab.contacts.domain.entities.User;
import io.inab.contacts.domain.models.UserSecurity;
import jakarta.security.auth.message.AuthException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * @param details
     * @return boolean
     */
    @Override
    public AuthResponse register(Login details) throws AuthException {
        if((details.getEmail() == null && details.getUsername() == null) || details.getPwd() == null)
            throw new AuthException("Credentials not provided!");

        var username = details.getUsername() == null ?
                this.getUsernameFromEmail(details.getEmail()) : details.getUsername();
        try {
            final String pwd = new BCryptPasswordEncoder().encode(details.getPwd());
            final UserDto user = this.usersService.register(new UserDto(username, details.getEmail(), null, null), pwd);
            final String token = this.jwtUtil.generateToken(new UserSecurity(this.mapper.map(user, User.class)));

            return new AuthResponse(user.getUsername(), token);
        } catch (Exception e) {
            throw new AuthException("Ups... something went wrong with your registration.");
        }

    }

    /**
     * @param details
     * @return boolean
     */
    @Override
    public AuthResponse login(Login details) throws AuthException {
        if((details.getEmail() == null && details.getUsername() == null) || details.getPwd() == null)
            throw new AuthException("Credentials not provided!");

        UserDto user = null;

        try {
            if(details.getEmail() != null)
                user = this.usersService.getByEmail(details.getEmail());
            else if(details.getUsername() != null)
                user = this.usersService.getByUsername(details.getUsername());
        } catch (Exception e) {
            throw new AuthException("Ups... something went wrong with your login. \nMessage:" + e.getMessage());
        }

        if(user == null) throw new UserNotFoundException("Ups... your user was not found!");

        final var credentials = new UsernamePasswordAuthenticationToken(user.getEmail(), details.getPwd());
        this.authenticationManager.authenticate(credentials);
        final String token = this.jwtUtil.generateToken(new UserSecurity(this.mapper.map(user, User.class)));

        return new AuthResponse(user.getUsername(), token);
    }

    private String getUsernameFromEmail(String email) {
        var split = email.split("[@._]");
        return split[0];
    }
}
