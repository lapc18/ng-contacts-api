package io.inab.contacts.domain.entities;

import io.inab.contacts.core.abstracts.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact_emails")
public class Email extends BaseEntity {

    @Column
    private String type;

    @Column(nullable = false)
    private String email;

    public Email() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
