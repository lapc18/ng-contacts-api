package io.inab.contacts.core.interfaces;

import io.inab.contacts.core.models.Login;
import io.inab.contacts.domain.dtos.UserDto;

public interface IAuthService {
    UserDto register(Login details) throws Exception;
    UserDto login(Login details) throws Exception;
}
