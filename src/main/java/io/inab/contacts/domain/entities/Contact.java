package io.inab.contacts.domain.entities;


import io.inab.contacts.core.abstracts.BaseEntity;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity {

    @Column
    private String profile;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String nickName;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Email> emails;

    @OneToMany(fetch = FetchType.LAZY)
    private List<PhoneNumber> phoneNumbers;

    @Column
    private String address;

    @Column
    private String website;

    @Column
    private String relationship;

    @Column
    private String notes;

    @Column
    private boolean isCompany;

    @Column
    private String company;

    public Contact() {
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
