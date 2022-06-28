package com.example.isystem_students.service;

import com.example.isystem_students.dto.GroupDto;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.Group;
import com.example.isystem_students.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private GroupTypesService groupTypesService;

    public GroupDto get(Integer id){
        Group group=getGroupEntity(id);
        GroupDto groupDto =new GroupDto();
        convertEntityToDto(group, groupDto);
        groupDto.setGroupTypesDto(groupTypesService.get(groupDto.getGroupTypesId()));
        groupDto.setCourseDto(courseService.get(groupDto.getGroupTypesId()));
        log.info("Get Group info {}", groupDto);
        return groupDto;
    }
    public GroupDto create(GroupDto groupDto){
        Optional< Group> optional =groupRepository.findByName(groupDto.getName());
        if (optional.isPresent()){
            throw new ServerBadRequestException(" Group with this already exist!");
        }
        Group group=new Group();
        groupTypesService.getGroupTypesEntity(groupDto.getGroupTypesId());
        courseService.getCourseEntity((groupDto.getCourseId()));
        convertDtoToEntity(groupDto, group);
        group.setCreatedAt(LocalDateTime.now());
        groupRepository.save(group);
        log.info("Create Group {}", group);
        return groupDto;
    }

    public GroupDto update(Integer id, GroupDto groupDto){
        Group group= getGroupEntity(id);
        group.setId(id);
        group.setUpdatedAt(LocalDateTime.now());
        groupRepository.save(group);
        log.info("Update Group {}", group);
        return groupDto;
    }

    public Boolean delete(Integer id){
        Group group =getGroupEntity(id);
        group.setDeletedAt(LocalDateTime.now());
        groupRepository.delete(group);
        log.info("Delete Group {}", group);
        return true;
    }

    private void convertDtoToEntity(GroupDto first, Group second) {
        first.setName(second.getName());
        first.setGroupTypesId(second.getGroupTypesId());
        first.setCourseId(second.getCourseId());
        first.setId(second.getId());

    }

    private  void convertEntityToDto(Group first, GroupDto second) {
        first.setName(second.getName());
        first.setGroupTypesId(second.getGroupTypesId());
        first.setCourseId(second.getCourseId());
    }

    public Group getGroupEntity(Integer id) {
        Optional<Group> optional =groupRepository.findById(id);
        if (optional.isEmpty() || optional.get().getDeletedAt() != null){
            throw  new ServerBadRequestException("Group does not exist");
        }
        return optional.get();
    }
}
