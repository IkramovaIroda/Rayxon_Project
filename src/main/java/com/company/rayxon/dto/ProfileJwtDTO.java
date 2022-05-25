package com.company.rayxon.dto;

import com.company.rayxon.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data

@NoArgsConstructor
public class ProfileJwtDTO {
    private Integer id;
    private Role role;

    public ProfileJwtDTO(Integer id, Role role) {
        this.id = id;
        this.role = role;
    }
}
