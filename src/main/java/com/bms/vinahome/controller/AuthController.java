package com.bms.vinahome.controller;

import com.bms.vinahome.dto.Auth.DTO_RQ_LoginGoogle;
import com.bms.vinahome.dto.User.DTO_RP_InfoUser;
import com.bms.vinahome.service.AuthenService;
import com.bms.vinahome.utils.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
@Autowired
    AuthenService authenService;

    // E1.US04: Google Login
    @PostMapping("/google-login")
    ApiResponse<DTO_RP_InfoUser> loginWithGoogle(@RequestBody DTO_RQ_LoginGoogle dto) {
        var result = authenService.loginWithGoogle(dto);
        return ApiResponse.<DTO_RP_InfoUser>builder()
                .code(200)
                .message("Đăng nhập thành công")
                .result(result)
                .build();
    }


}
