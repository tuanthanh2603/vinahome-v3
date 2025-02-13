package com.bms.vinahome.dto.User;

import lombok.Data;

import java.util.Date;

@Data
public class DTO_RP_InfoUser {
    private Long id;
    private String email;
    private String name;
    private String picture;
    private String phone;
    private String accountType;
    private Integer gender;
    private Date birthday;

}
