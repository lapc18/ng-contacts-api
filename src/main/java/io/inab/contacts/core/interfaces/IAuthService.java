package io.inab.contacts.core.interfaces;

import io.inab.contacts.core.models.Login;

public interface IAuthService {
    boolean register(Login details) throws Exception;
    boolean login(Login details) throws Exception;
}
