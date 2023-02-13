package io.inab.contacts.domain.dtos;

import io.inab.contacts.core.abstracts.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ContactDto extends BaseDto {

    @Getter
    @Setter
    private String profile;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private String nickName;

    @Getter @Setter
    private String emails;

    @Getter @Setter
    private String phoneNumbers;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String website;

    @Getter @Setter
    private String relationship;

    @Getter @Setter
    private String notes;

    @Getter @Setter
    private boolean isCompany;

    @Getter @Setter
    private String company;
}
