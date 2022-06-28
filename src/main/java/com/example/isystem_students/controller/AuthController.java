package com.example.isystem_students.controller;

import com.example.isystem_students.dto.AuthDto;
import com.example.isystem_students.dto.RegisterDto;
import com.example.isystem_students.dto.UserDto;
import com.example.isystem_students.model.AuthData;
import com.example.isystem_students.util.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto dto){
        AuthData result = authService.register(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDto dto){
        AuthDto result = authService.login(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/verification/{token}")
    public ResponseEntity<?> verification(@PathVariable("token") String token){
        UserDto result = authService.verification(token);
        return ResponseEntity.ok(result);
    }
}

