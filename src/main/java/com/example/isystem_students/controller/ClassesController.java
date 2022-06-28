package com.example.isystem_students.controller;

import com.example.isystem_students.dto.ClassesDto;
import com.example.isystem_students.dto.UserDto;
import com.example.isystem_students.service.ClassesService;
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
@RequestMapping("/api/v1/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable("id") Integer id) {
        ClassesDto result = classesService.get(id);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("class", result))
                        .message("class retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody ClassesDto classesDto) {
        ClassesDto result = classesService.create(classesDto);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("class", result))
                        .message("class create")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping("/update/{id}")
    public HttpEntity<?> update(@PathVariable("id") Integer id,
                                @RequestBody ClassesDto classesDto) {
        ClassesDto result = classesService.update(id, classesDto);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("class", result))
                        .message("class updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Integer id) {
        Boolean result = classesService.delete(id);
        return ResponseEntity.ok().body(
                Response.builder()
                        .timeStep(LocalDateTime.now())
                        .data(Map.of("class", result))
                        .message("class deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}

