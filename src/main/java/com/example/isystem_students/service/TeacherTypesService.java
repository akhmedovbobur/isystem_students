package com.example.isystem_students.service;

import com.example.isystem_students.dto.TeacherTypesDto;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.TeacherTypes;
import com.example.isystem_students.repository.TeacherTypesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TeacherTypesService {

    @Autowired
    private TeacherTypesRepository teacherTypesRepository;

    public TeacherTypesDto get(Integer id){

        return convertEntityToDto(getTeacherTypesEntity(id, new TeacherTypesDto()));
    }

    public void create(TeacherTypesDto teacherTypesDto){
        TeacherTypes teacherTypes =convertDtoToEntity(teacherTypesDto);
        teacherTypes.setStatus(true);
        teacherTypes.setCreatedAt(LocalDateTime.now());
        teacherTypesRepository.save(teacherTypes);
    }

    public void update(Integer id,
                       TeacherTypesDto teacherTypesDto){
        TeacherTypes teacherTypes=getTeacherTypesEntity(id, new TeacherTypesDto());
        teacherTypes.setName(teacherTypesDto.getName());
        teacherTypes.setDisplayName(teacherTypesDto.getDisplayName());
        teacherTypes.setUpdatedAt(LocalDateTime.now());
        teacherTypesRepository.save(teacherTypes);
    }

    public void delete(Integer id){
        TeacherTypes teacherTypes=getTeacherTypesEntity(id, new TeacherTypesDto());
        teacherTypes.setDeletedAt(LocalDateTime.now());
        teacherTypesRepository.delete(teacherTypes);
    }


    public static TeacherTypesDto convertEntityToDto(TeacherTypes teacherTypes){
        return TeacherTypesDto.builder()
                .id(teacherTypes.getId())
                .name(teacherTypes.getName())
                .displayName(teacherTypes.getDisplayName())
                .status(teacherTypes.getStatus())
                .build();
    }

    public static TeacherTypes convertDtoToEntity(TeacherTypesDto teacherTypesDto){
        return TeacherTypes.builder()
                .id(teacherTypesDto.getId())
                .name(teacherTypesDto.getName())
                .displayName(teacherTypesDto.getDisplayName())
                .status(teacherTypesDto.getStatus())
                .build();
    }

    public TeacherTypes getTeacherTypesEntity(Integer id, TeacherTypesDto teacherTypesDto){
        Optional<TeacherTypes> optional = teacherTypesRepository.findById(id);
        if (optional.isEmpty()){
            throw new ServerBadRequestException(" AttendanceType does not exist!");
        }
        return optional.get();
    }
}
