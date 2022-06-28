package com.example.isystem_students.controller;

import com.example.isystem_students.dto.GroupTypesDto;
import com.example.isystem_students.service.GroupTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groupTypes")
public class GroupTypesController {

    @Autowired
    private GroupTypesService groupTypesService;

    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable("id")Integer id) {
        GroupTypesDto result = groupTypesService.get(id);
        return ResponseEntity.ok().body(result);
    }
    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody GroupTypesDto groupTypesDto){
        groupTypesService.create(groupTypesDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public HttpEntity<?> update(@PathVariable("id")Integer id,
                                @RequestBody GroupTypesDto groupTypesDto){
        groupTypesService.update(id, groupTypesDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable("id")Integer id){
        groupTypesService.delete(id);
        return ResponseEntity.ok().build();
    }
}
