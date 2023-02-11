package io.inab.contacts.domain.entities;

import io.inab.contacts.core.abstracts.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact_phone_numbers")
public class PhoneNumber extends BaseEntity {

    private String type;

    private String phone;

}
