package com.example.isystem_students.service;

import com.example.isystem_students.dto.TeacherDto;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.Teacher;
import com.example.isystem_students.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class TeacherService {

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherTypesService teacherTypesService;

    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherDto get(Integer id){
        Teacher teacher = getTeacherEntity(id);
        TeacherDto teacherDto= new TeacherDto();
        convertEntityToDto(teacher, teacherDto);
        teacherDto.setTeacherTypesDto(teacherTypesService.get(teacherDto.getTeacherTypesId()));
        teacherDto.setUserDto(userService.get(teacherDto.getUserId()));
        log.info("Get Teacher Info {}", teacherDto);
        return teacherDto;
    }

    public TeacherDto create(TeacherDto teacherDto){
        Teacher teacher =new Teacher();
        userService.getUserEntity(teacherDto.getUserId());
        teacherTypesService.getTeacherTypesEntity(teacherDto.getTeacherTypesId(),teacherDto.getTeacherTypesDto());
        convertDtoToEntity(teacherDto, teacher);
        teacher.setCreatedAt(LocalDateTime.now());
        teacher.setStatus(true);
        teacher.setId(teacherDto.getId());
        teacher.setUserId(teacherDto.getUserId());
        teacherRepository.save(teacher);
        log.info("Create teacher {}", teacher);
        return teacherDto;
    }

    public TeacherDto update(Integer id, TeacherDto teacherDto){
        Teacher teacher =getTeacherEntity(id);
        teacher.setId(id);
        teacher.setTeacherTypesId(teacherDto.getTeacherTypesId());
        teacher.setUserId(teacherDto.getUserId());
        teacher.setUpdatedAt(LocalDateTime.now());
        teacherRepository.save(teacher);
        log.info("Update Teacher {}", teacher);
        return teacherDto;

    }
    public Boolean delete(Integer id){
        Teacher teacher=getTeacherEntity(id);
        teacher.setDeletedAt(LocalDateTime.now());
        teacherRepository.delete(teacher);
        log.info("Delete Teacher {}", teacher);
        return true;
    }


    private   void convertEntityToDto(Teacher first, TeacherDto second){
        first.setUserId(second.getUserId());
        first.setTeacherTypesId(second.getTeacherTypesId());
    }
    private void convertDtoToEntity(TeacherDto first, Teacher second){
        first.setId(second.getId());
        first.setUserId(second.getUserId());
        first.setTeacherTypesId(second.getTeacherTypesId());

    }

    public Teacher getTeacherEntity(Integer id) {
        Optional<Teacher> optional = teacherRepository.findById(id);
        if (optional.isEmpty() || optional.get().getDeletedAt() != null) {
            throw new ServerBadRequestException("Teacher does not exist !");
        }
        return optional.get();
    }
}
