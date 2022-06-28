package com.example.isystem_students.controller;

import com.example.isystem_students.dto.RoomsDto;
import com.example.isystem_students.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomsController {

    @Autowired
    private RoomsService roomsService;

    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable("id") Integer id) {
        RoomsDto result = roomsService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody RoomsDto roomsDto) {
        roomsService.create(roomsDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public HttpEntity<?> update(@PathVariable("id") Integer id,
                                @RequestBody RoomsDto roomsDto) {
        roomsService.update(id, roomsDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        roomsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
