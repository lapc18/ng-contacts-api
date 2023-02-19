package io.inab.contacts.core.interfaces;

import io.inab.contacts.core.models.AuthResponse;
import io.inab.contacts.core.models.Login;
import io.inab.contacts.domain.dtos.UserDto;
import jakarta.security.auth.message.AuthException;

public interface IAuthService {
    AuthResponse register(Login details) throws AuthException;
    AuthResponse login(Login details) throws Exception;
}
