package com.example.isystem_students.service;

import com.example.isystem_students.dto.CourseDto;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.Course;
import com.example.isystem_students.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CourseService {

    @Autowired
    private  CourseRepository courseRepository;

    public  CourseDto get(Integer id) {
        return convertEntityToDto(getCourseEntity(id));
    }

    public void create(CourseDto courseDto){
        Course course = convertDtoToEntity(courseDto);
        course.setStatus(true);
        course.setCreatedAt(LocalDateTime.now());
        courseRepository.save(course);
    }


    public void update(Integer id,
                       CourseDto courseDto){
        Course course  = getCourseEntity(id);
        course.setName(courseDto.getName());
        course.setDisplayName(courseDto.getDisplayName());
        course.setUpdatedAt(LocalDateTime.now());
        courseRepository.save(course);
    }
    public void delete(Integer id){
        Course course= getCourseEntity(id);
        course.setDeletedAt(LocalDateTime.now());
        courseRepository.delete(course);
    }

    public  Course getCourseEntity(Integer id){
        Optional<Course> optional =courseRepository.findById(id);
        if (optional.isEmpty()){
            throw  new ServerBadRequestException("Course does not exist");
        }
        return optional.get();
    }

    public static  Course convertDtoToEntity(CourseDto courseDto){
        return Course.builder()
                .id(courseDto.getId())
                .name(courseDto.getName())
                .displayName(courseDto.getDisplayName())
                .status(courseDto.getStatus())
                .build();
    }
    public static CourseDto  convertEntityToDto(Course course){
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .displayName(course.getDisplayName())
                .status(course.getStatus())
                .build();
    }
}
