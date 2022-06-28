package com.example.isystem_students.controller;

import com.example.isystem_students.dto.UserDto;
import com.example.isystem_students.service.UserService;
import com.example.isystem_students.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable("id") Integer id) {
        UserDto result = userService.get(id);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("user", result))
                        .message("User retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody UserDto user) {
        UserDto result = userService.create(user);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("user", result))
                        .message("User create")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping("/update/{id}")
    public HttpEntity<?> update(@PathVariable("id") Integer id,
                                @RequestBody UserDto userDto) {
        UserDto result = userService.update(id, userDto);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("user", result))
                        .message("User updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Integer id) {
        Boolean result = userService.delete(id);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("user", result))
                        .message("User deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
    @PatchMapping("/create-admin/{id}")
    public ResponseEntity<?> changeUserToAdmin(@PathVariable("id") Integer id){
        boolean result = userService.changeUserToAdmin(id);
        return ResponseEntity.ok(result);
    }
}
