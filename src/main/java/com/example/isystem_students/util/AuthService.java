package com.example.isystem_students.util;

import com.example.isystem_students.application.JwtTokenUtil;
import com.example.isystem_students.dto.AuthDto;
import com.example.isystem_students.dto.RegisterDto;
import com.example.isystem_students.dto.UserDto;
import com.example.isystem_students.enums.Roles;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.AuthData;
import com.example.isystem_students.model.User;
import com.example.isystem_students.model.UserTypes;
import com.example.isystem_students.repository.UserRepository;
import com.example.isystem_students.repository.UserTypesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AuthService {
    private MessageService messageService;
    private JwtTokenUtil jwtTokenUtil;

    private UserRepository userRepository;
    private UserTypesRepository userTypesRepository;
    private PasswordEncoder passwordEncoder;

    public AuthData register(RegisterDto dto) {
        Optional<User> optional = userRepository.
                findByEmailOrPhoneAndDeletedAtIsNull(dto.getEmail(),dto.getPhone());
        if (optional.isPresent()){
            throw new ServerBadRequestException("User already exist");
        }
        User users = new User();
        users.setEmail(dto.getEmail());
        users.setPhone(dto.getPhone());
        users.setPassword(passwordEncoder.encode(dto.getPassword()));
        users.setStatus(false);
        userRepository.save(users);

        UserTypes userTypes = new UserTypes();
        userTypes.setName(String.valueOf(Roles.ROLE_USER));
        userTypes.setStatus(true);
        userTypesRepository.save(userTypes);

        String token = jwtTokenUtil.generateAccessToken(users.getId(), users.getEmail());
        String link = "http://localhost:8080/api/v1/auth/verification/" + token;
        String content = String.format("Please click %s for verification",link);

        AuthData authData = new AuthData();
        authData.setToken(token);
        authData.setLink(link);
        authData.setContent(content);
        return authData;
    }

    public AuthDto login(AuthDto dto) {
        Optional<User> optional = userRepository
                .findByEmailAndPasswordAndDeletedAtIsNull(dto.getEmail(), PasswordService.generateMD5(dto.getPassword()));
        if (optional.isEmpty()){
            throw new ServerBadRequestException("User not found");
        }
        User users = optional.get();
        String token = jwtTokenUtil.generateAccessToken(users.getId(), users.getEmail());
        dto.setToken(token);
        return dto;
    }

    public UserDto verification(String token) {
        String userName = jwtTokenUtil.getUserName(token);
        Optional<User> optional = userRepository.findByEmailAndDeletedAtIsNull(userName);
        if (optional.isEmpty()){
            throw new ServerBadRequestException("User not found");
        }
        User users = optional.get();
        users.setStatus(true);
        userRepository.save(users);
        UserDto dto = new UserDto();
        dto.setEmail(users.getEmail());
        dto.setPassword(users.getPassword());
        dto.setFirstName(users.getFirstName());
        return dto;
    }

}
