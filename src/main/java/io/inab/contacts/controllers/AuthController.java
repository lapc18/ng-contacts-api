package io.inab.contacts.controllers;

import io.inab.contacts.core.models.Login;
import io.inab.contacts.infrastructure.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Login login
    ) {
        try {
            var dto = this.authService.login(login);
            if(dto != null) return ResponseEntity.ok(dto);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);

        } catch (Exception e) {
            var response = new HashMap<String, Object>();

            response.put("code", "HTTP500");
            response.put("exception", "Something went wrong with your login.");
            response.put("stackTrace", e.getStackTrace());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody Login login
    ) {
        try {
            var dto = this.authService.register(login);
            if(dto != null) return ResponseEntity.ok(dto);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
        } catch (Exception e) {
            var response = new HashMap<String, Object>();

            response.put("code", "HTTP500");
            response.put("exception", "Something went wrong with your registration.");
            response.put("stackTrace", e.getStackTrace());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
