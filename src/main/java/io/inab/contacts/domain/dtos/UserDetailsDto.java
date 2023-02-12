package io.inab.contacts.domain.dtos;

import io.inab.contacts.core.abstracts.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto extends BaseDto {

    @Getter
    @Setter
    private String name;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private String profile;

    @Getter @Setter
    private String phone;
}
