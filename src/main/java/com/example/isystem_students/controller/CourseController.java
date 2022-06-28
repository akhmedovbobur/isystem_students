package com.example.isystem_students.controller;

import com.example.isystem_students.dto.CourseDto;
import com.example.isystem_students.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable("id")Integer id){
        CourseDto result=courseService.get(id);
        return ResponseEntity.ok().body(result);
    }
    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody CourseDto courseDto){
        courseService.create(courseDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public HttpEntity<?>update(@PathVariable("id")Integer id,
                               @RequestBody CourseDto courseDto){
        courseService.update(id, courseDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable("id")Integer id){
        courseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
