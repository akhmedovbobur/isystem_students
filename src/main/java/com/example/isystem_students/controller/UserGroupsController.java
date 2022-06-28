package com.example.isystem_students.controller;

import com.example.isystem_students.dto.UserGroupsDto;
import com.example.isystem_students.service.UserGroupsService;
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
@RequestMapping("/api/v1/user_groups")
public class UserGroupsController {

    @Autowired
    private UserGroupsService userGroupsService;

    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable("id") Integer id) {
        UserGroupsDto result = userGroupsService.get(id);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("user groups", result))
                        .message("user groups retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody UserGroupsDto userGroupsDto) {
        UserGroupsDto result = userGroupsService.create(userGroupsDto);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("user groups", result))
                        .message("user groups create")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping("/update/{id}")
    public HttpEntity<?> update(@PathVariable("id") Integer id,
                                @RequestBody UserGroupsDto userGroupsDto) {
        UserGroupsDto result = userGroupsService.update(id, userGroupsDto);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("user groups", result))
                        .message("user groups updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Integer id) {
        Boolean result = userGroupsService.delete(id);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("user groups", result))
                        .message("user groups deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
