package com.example.isystem_students.service;

import com.example.isystem_students.dto.AttendanceTypeDto;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.AttendanceType;
import com.example.isystem_students.repository.AttendanceTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AttendanceTypeService {
    @Autowired
    private AttendanceTypeRepository  attendanceTypeRepository;


    public AttendanceTypeDto get(Integer id){
        return convertEntityToDto(getAttendanceTypeEntity(id));

    }

    public void create(AttendanceTypeDto attendanceTypeDto){
        AttendanceType attendanceType = convertDtoToEntity(attendanceTypeDto);
       attendanceType.setStatus(true);
       attendanceType.setCreatedAt(LocalDateTime.now());
       attendanceTypeRepository.save(attendanceType);
    }

    public void update(Integer id,
                       AttendanceTypeDto attendanceTypeDto){
        AttendanceType attendanceType = getAttendanceTypeEntity(id);
        attendanceType.setName(attendanceTypeDto.getName());
        attendanceType.setReasonMessage(attendanceTypeDto.getReasonMessage());
        attendanceType.setUpdatedAt(LocalDateTime.now());
        attendanceTypeRepository.save(attendanceType);
    }

    public void delete(Integer id){
        AttendanceType attendanceType= getAttendanceTypeEntity(id);
        attendanceType.setDeletedAt(LocalDateTime.now());
        attendanceTypeRepository.delete(attendanceType);
    }

    public AttendanceType getAttendanceTypeEntity(Integer id){
        Optional<AttendanceType> optional = attendanceTypeRepository.findById(id);
        if (optional.isEmpty()){
            throw new ServerBadRequestException(" AttendanceType does not exist!");
        }
        return optional.get();
    }

    public  AttendanceTypeDto convertEntityToDto(AttendanceType attendanceType){
        return AttendanceTypeDto.builder()
                .id(attendanceType.getId())
                .name(attendanceType.getName())
                .reasonMessage(attendanceType.getReasonMessage())
                .status(attendanceType.getStatus())
                .build();
    }

    public  AttendanceType convertDtoToEntity(AttendanceTypeDto attendanceTypeDto){
        return AttendanceType.builder()
                .id(attendanceTypeDto.getId())
                .name(attendanceTypeDto.getName())
                .reasonMessage(attendanceTypeDto.getReasonMessage())
                .status(attendanceTypeDto.getStatus())
                .build();
    }
}
