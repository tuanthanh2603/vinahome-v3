package com.bms.vinahome.modules.ModuleEmployee.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DTO_RQ_CreateEmployee {
    String username;
    String password;
    Boolean accessBms;
    Boolean accessCms;
    Boolean accessTms;
    String fullName;
    String phone;
    LocalDate startDate;
    LocalDate birthDate;
    Integer gender;
    String email;
    String address;
    Boolean status;
    Set<String> roles;
    Long companyId;
}
