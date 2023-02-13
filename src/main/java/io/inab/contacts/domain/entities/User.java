package io.inab.contacts.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.inab.contacts.core.abstracts.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false, updatable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String pwd;

    @OneToOne(fetch = FetchType.LAZY)
    private UserDetails details;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Contact> contacts;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}