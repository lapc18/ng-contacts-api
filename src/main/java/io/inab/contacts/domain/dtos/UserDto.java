package io.inab.contacts.domain.dtos;

import io.inab.contacts.core.abstracts.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

    @Getter
    @Setter
    private String username;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private UserDetailsDto details;

    @Getter @Setter
    private List<ContactDto> contacts;

}
