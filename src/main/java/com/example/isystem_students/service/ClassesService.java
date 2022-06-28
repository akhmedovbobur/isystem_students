package com.example.isystem_students.service;

import com.example.isystem_students.dto.*;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.Classes;
import com.example.isystem_students.repository.ClassesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClassesService {

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private UserGroupsService userGroupsService;

    @Autowired
    private AttendanceTypeService attendanceTypeService;

    @Autowired
    private ClassesRepository classesRepository;

    public ClassesDto get(Integer id) {
        Classes classes = getClassesEntity(id);
        ClassesDto classesDto = new ClassesDto();
        convertEntityToDto(classes, classesDto);
        classesDto.setRoomsDto(roomsService.get(classesDto.getRoomsId()));
        classesDto.setAttendanceTypeDto(attendanceTypeService.get(classesDto.getRoomsId()));
        classesDto.setUserGroupsDto(userGroupsService.get(classesDto.getUserGroupsId()));
        log.info("Get Classes Info {}", classesDto);
        return classesDto;
    }

    public ClassesDto create(ClassesDto classesDto) {
        Optional<Classes> optional = classesRepository.findByName(classesDto.getName());
        if (optional.isPresent()) {
            throw new ServerBadRequestException("Classes with this already exist");
        }
        Classes classes = new Classes();
        roomsService.getRoomsEntity(classesDto.getRoomsId());
        attendanceTypeService.getAttendanceTypeEntity(classesDto.getAttendanceTypeId());
        userGroupsService.getUserGroupsEntity(classesDto.getUserGroupsId());
        convertDtoToEntity(classesDto, classes);
        classes.setName(classesDto.getName());
        classes.setDate(classesDto.getDate());
        classes.setCreatedAt(LocalDateTime.now());
        classes.setStatus(true);
        classesRepository.save(classes);
        log.info("Create Classes {}", classes);
        return classesDto;
    }


    public ClassesDto update(Integer id, ClassesDto classesDto) {
        Classes classes = getClassesEntity(id);
        classes.setId(id);
        classes.setName(classesDto.getName());
        classes.setDate(classesDto.getDate());
        classes.setUpdatedAt(LocalDateTime.now());
        classesRepository.save(classes);
        log.info("Update Classes {}", classes);
        return classesDto;
    }

    public Boolean delete(Integer id) {
        Classes classes = getClassesEntity(id);
        classes.setDeletedAt(LocalDateTime.now());
        classesRepository.delete(classes);
        log.info("Delete Classes {}", classes);
        return true;
    }

    private void convertEntityToDto(Classes first, ClassesDto second) {
        first.setName(second.getName());
        first.setDate(second.getDate());
        first.setAttendanceTypeId(second.getAttendanceTypeId());
        first.setRoomsId(second.getRoomsId());
        first.setUserGroupsId(second.getUserGroupsId());
    }

    private void convertDtoToEntity(ClassesDto first, Classes second) {
     first.setId(second.getId());
     first.setName(second.getName());
     first.setDate(second.getDate());
     first.setUserGroupsId(second.getUserGroupsId());
     first.setRoomsId(second.getRoomsId());
     first.setAttendanceTypeId(second.getAttendanceTypeId());

    }

    private Classes getClassesEntity(Integer id) {
        Optional<Classes> optional = classesRepository.findById(id);
        if (optional.isEmpty() || optional.get().getDeletedAt() != null || !optional.get().getStatus()) {
            throw new ServerBadRequestException("Classes does not exist !");
        }
        return optional.get();
    }
}