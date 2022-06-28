package com.example.isystem_students.controller;

import com.example.isystem_students.dto.GroupDto;
import com.example.isystem_students.dto.UserDto;
import com.example.isystem_students.repository.GroupRepository;
import com.example.isystem_students.service.CourseService;
import com.example.isystem_students.service.GroupService;
import com.example.isystem_students.service.GroupTypesService;
import com.example.isystem_students.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable("id") Integer id) {
        GroupDto result = groupService.get(id);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("group", result))
                        .message("Group retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping()
    public HttpEntity<?> create(@RequestBody GroupDto groupDto) {
        GroupDto result = groupService.create(groupDto);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("group", result))
                        .message("Group create")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable("id") Integer id,
                                @RequestBody GroupDto groupDto) {
        GroupDto result = groupService.update(id, groupDto);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("group", result))
                        .message("Group updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Integer id) {
        Boolean result = groupService.delete(id);
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
}
