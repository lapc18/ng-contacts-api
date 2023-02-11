package io.inab.contacts.domain.dtos;

import io.inab.contacts.core.abstracts.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class EmailDto extends BaseDto {

    @Getter @Setter
    private String type;

    @Getter @Setter
    private String email;

}
