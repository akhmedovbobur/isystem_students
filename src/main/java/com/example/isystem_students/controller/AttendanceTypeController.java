package com.example.isystem_students.controller;

import com.example.isystem_students.dto.AttendanceTypeDto;
import com.example.isystem_students.service.AttendanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendanceType")
public class AttendanceTypeController {

    @Autowired
    private AttendanceTypeService attendanceTypeService;


    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody AttendanceTypeDto attendanceTypeDto) {
        attendanceTypeService.create(attendanceTypeDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable("id") Integer id) {
        AttendanceTypeDto result = attendanceTypeService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/update/{id}")
    public HttpEntity<?> update(@PathVariable("id") Integer id,
                                @RequestBody AttendanceTypeDto attendanceTypeDto) {
        attendanceTypeService.update(id, attendanceTypeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        attendanceTypeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
