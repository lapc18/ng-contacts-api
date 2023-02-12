package io.inab.contacts.controllers;

import io.inab.contacts.domain.dtos.UserDto;
import io.inab.contacts.infrastructure.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/api/contacts")
public class ContactsController {

    @Autowired
    private UsersService service;

    @PostMapping(value = "")
    public ResponseEntity<?> create(
            @RequestBody(required = true) UserDto body
    ) throws Exception {
        try {
            return ResponseEntity.ok(this.service.create(body));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<?> update(
            @RequestBody(required = true) UserDto body
    ) throws Exception {
        try {
            return ResponseEntity.ok(this.service.update(body));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10")  int size
    ) throws Exception {
        try {
            return ResponseEntity.ok(this.service.getAll(PageRequest.of(page, size)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    @DeleteMapping(value = "")
    public ResponseEntity<?> delete(
            @RequestParam(defaultValue = "true") boolean softDelete,
            @RequestParam() int id
    ) throws Exception {
        try {
            if(softDelete)
                this.service.softDelete(id);
            else
                this.service.hardDelete(id);

            var res = new HashMap<String, String>();
            res.put("status", "deleted");
            res.put("deleted", String.valueOf(id));

            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

}
