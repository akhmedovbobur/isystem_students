package com.example.isystem_students.controller;

import com.example.isystem_students.dto.UserTypesDto;
import com.example.isystem_students.service.UserTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userType")
public class UserTypesController {

    @Autowired
    private UserTypesService userTypesService;

    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable("id") Integer id){
        UserTypesDto result = userTypesService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/create")
    public  HttpEntity<?> create(@RequestBody UserTypesDto userTypesDto){
        userTypesService.create(userTypesDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public  HttpEntity<?> update(@PathVariable("id")Integer id,
                                 @RequestBody UserTypesDto userTypesDto){
        userTypesService.update(id, userTypesDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public  HttpEntity<?> delete(@PathVariable("id")Integer id){
        userTypesService.delete(id);
        return ResponseEntity.ok().build();
    }
}
