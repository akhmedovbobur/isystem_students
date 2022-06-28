package com.example.isystem_students.controller;

import com.example.isystem_students.dto.TeacherDto;
import com.example.isystem_students.dto.TeacherTypesDto;
import com.example.isystem_students.model.TeacherTypes;
import com.example.isystem_students.service.TeacherTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacherTypes")
public class TeacherTypesController {

    @Autowired
    private TeacherTypesService teacherTypesService;

    @GetMapping("/get{id}")
    public HttpEntity<?> get(@PathVariable("id") Integer id) {
        TeacherTypesDto result = teacherTypesService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody TeacherTypesDto teacherTypesDto){
        teacherTypesService.create(teacherTypesDto);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/update/{id}")
    public  HttpEntity<?> update(@RequestBody TeacherTypesDto teacherTypesDto,
                                 @PathVariable("id") Integer id){
        teacherTypesService.update(id, teacherTypesDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Integer id){
        teacherTypesService.delete(id);
        return ResponseEntity.ok().build();
    }
}
