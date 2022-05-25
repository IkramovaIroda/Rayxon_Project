package com.company.rayxon.dto;




import com.company.rayxon.enums.ProfileOrderStatus;
import com.company.rayxon.enums.ProfileStatus;
import lombok.Data;

import javax.management.relation.Role;
import java.time.LocalDate;

@Data
public class ProfileFilterDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private Role role;
    private ProfileStatus status;
    private LocalDate fromDate;
    private LocalDate toDate;
    private ProfileOrderStatus orderBy = ProfileOrderStatus.ID_ASC;
}
