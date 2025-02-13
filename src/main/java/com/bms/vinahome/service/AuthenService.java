package com.bms.vinahome.service;

import com.bms.vinahome.dto.Auth.DTO_RP_LoginGoogle;
import com.bms.vinahome.dto.Auth.DTO_RQ_LoginGoogle;
import com.bms.vinahome.dto.User.DTO_RP_InfoUser;
import com.bms.vinahome.exception.AppException;
import com.bms.vinahome.exception.ErrorCode;
import com.bms.vinahome.mapper.UserMapper;
import com.bms.vinahome.model.User;
import com.bms.vinahome.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenService {
    @Autowired
    UserRepository userRepository;
    private static final String GOOGLE_USER_INFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

    public DTO_RP_InfoUser loginWithGoogle(DTO_RQ_LoginGoogle dto) {
        String accessToken = dto.getAccessToken();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<DTO_RP_LoginGoogle> response = restTemplate.exchange(
                GOOGLE_USER_INFO_URL, HttpMethod.GET, entity, DTO_RP_LoginGoogle.class
        );

        DTO_RP_LoginGoogle googleUserInfo = response.getBody();
        System.out.println("Google User Info: " + googleUserInfo);

        if (googleUserInfo == null || googleUserInfo.getEmail() == null) {
            throw new AppException(ErrorCode.GOOGLE_AUTH_FAILED);
        }

        User user = userRepository.findByEmail(googleUserInfo.getEmail());

        if (user == null) {
            System.out.println("Email chưa tồn tại");
            user = new User();
            user.setEmail(googleUserInfo.getEmail());
            user.setFullName(googleUserInfo.getName());
            user.setAccountType("GOOGLE");
            user.setAvatar(googleUserInfo.getPicture());
            userRepository.save(user);
        }

        return UserMapper.entityToDTO_InfoUser(user);
    }
}
