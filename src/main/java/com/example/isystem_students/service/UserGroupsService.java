package com.example.isystem_students.service;

import com.example.isystem_students.dto.*;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.UserGroups;
import com.example.isystem_students.repository.UserGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserGroupsService {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserGroupRepository userGroupsRepository;

    public UserGroupsDto get(Integer id) {
        UserGroups userGroups =getUserGroupsEntity(id);
        UserGroupsDto userGroupsDto =new UserGroupsDto();
        convertEntityToDto(userGroups, userGroupsDto);
        userGroupsDto.setGroupDto(groupService.get(userGroupsDto.getGroupId()));
        userGroupsDto.setUserDto(userService.get(userGroupsDto.getUserId()));
        userGroupsDto.setTeacherDto(teacherService.get(userGroupsDto.getTeacherId()));
        log.info("Get User groups Info {}", userGroupsDto);
        return userGroupsDto;
    }

    public UserGroupsDto create(UserGroupsDto userGroupsDto) {
        UserGroups userGroups= new UserGroups();
        groupService.getGroupEntity(userGroupsDto.getGroupId());
        userService.getUserEntity(userGroupsDto.getUserId());
        teacherService.getTeacherEntity(userGroupsDto.getTeacherId());
        convertDtoToEntity(userGroupsDto, userGroups);
        userGroups.setCreatedAt(LocalDateTime.now());
        userGroups.setStatus(true);
        userGroups.setTeacherId(userGroupsDto.getTeacherId());
        userGroups.setGroupId(userGroupsDto.getGroupId());
        userGroups.setGroupId(userGroupsDto.getGroupId());
        userGroupsRepository.save(userGroups);
        log.info("Create User groups {}", userGroups);
        return userGroupsDto;
    }

    public UserGroupsDto update(Integer id, UserGroupsDto userGroupsDto){
        UserGroups userGroups =getUserGroupsEntity(id);
        userGroups.setId(id);
        userGroups.setGroupId(userGroupsDto.getGroupId());
        userGroups.setTeacherId(userGroupsDto.getTeacherId());
        userGroups.setUserId(userGroupsDto.getUserId());
        userGroups.setUpdatedAt(LocalDateTime.now());
        userGroupsRepository.save(userGroups);
        log.info("Update User Groups {}", userGroups);
        return userGroupsDto;
    }

    public Boolean delete(Integer id){
        UserGroups userGroups=getUserGroupsEntity(id);
        userGroups.setDeletedAt(LocalDateTime.now());
        userGroupsRepository.delete(userGroups);
        log.info("Delete User groups {}", userGroups);
        return true;
    }

    private void convertEntityToDto(UserGroups first, UserGroupsDto second) {
        first.setUserId(second.getUserId());
        first.setGroupId(second.getGroupId());
        first.setTeacherId(second.getTeacherId());


    }
    private void convertDtoToEntity(UserGroupsDto first, UserGroups second){
        first.setId(second.getId());
        first.setUserId(second.getUserId());
        first.setGroupId(second.getGroupId());
        first.setTeacherId(second.getTeacherId());

    }


    public UserGroups getUserGroupsEntity(Integer id) {
        Optional<UserGroups>optional =userGroupsRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()|| optional.get().getDeletedAt()!=null){
            throw new ServerBadRequestException("User groups does not exist!");
        }
        return optional.get();
    }
}

