package com.bms.vinahome.mapper;

import com.bms.vinahome.dto.User.DTO_RP_InfoUser;
import com.bms.vinahome.model.User;

public class UserMapper {
    public static DTO_RP_InfoUser entityToDTO_InfoUser(User user) {
        DTO_RP_InfoUser dto = new DTO_RP_InfoUser();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getFullName());
        dto.setPhone(user.getPhone());
        dto.setPicture(user.getAvatar());
        dto.setAccountType(user.getAccountType());
        dto.setBirthday(user.getBirth());
        dto.setGender(user.getGender());
        return dto;
    }
}
