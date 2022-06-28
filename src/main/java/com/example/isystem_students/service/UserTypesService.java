package com.example.isystem_students.service;

import com.example.isystem_students.dto.UserTypesDto;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.UserTypes;
import com.example.isystem_students.repository.UserTypesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserTypesService {

    @Autowired
    private UserTypesRepository userTypesRepository;

    public UserTypesDto get(Integer id) {
        UserTypes entity = getUserTypeEntity(id);
        log.info("Get User Type Info {}", entity);
        return convertEntityToDto(entity, new UserTypesDto());
    }

    public UserTypesDto create(UserTypesDto userTypesDto) {
        UserTypes userTypes = convertDtoToEntity(userTypesDto, new UserTypes());
        userTypes.setStatus(true);
        userTypes.setCreatedAt(LocalDateTime.now());
        userTypesRepository.save(userTypes);
        log.info("Create User Type {}", userTypes);
        return userTypesDto;
    }

    public UserTypesDto update(Integer id, UserTypesDto userTypesDto) {
        UserTypes userTypes = getUserTypeEntity(id);
        convertDtoToEntity(userTypesDto, userTypes);
        userTypes.setId(id);
        userTypes.setUpdatedAt(LocalDateTime.now());
        userTypesRepository.save(userTypes);
        log.info("Update User Type {}", userTypes);
        return userTypesDto;
    }

    public Boolean delete(Integer id) {
        UserTypes userTypes = getUserTypeEntity(id);
        userTypes.setDeletedAt(LocalDateTime.now());
        userTypesRepository.save(userTypes);
        log.info("Delete User Type {}", userTypes);
        return true;
    }

    public UserTypes getUserTypeEntity(Integer id) {
        Optional<UserTypes> optional = userTypesRepository.findById(id);
        if (optional.isEmpty() || optional.get().getDeletedAt() != null || !optional.get().getStatus()) {
            throw new ServerBadRequestException("UserType does not exist!");
        }
        return optional.get();
    }


    public UserTypesDto convertEntityToDto(UserTypes first, UserTypesDto second) {
        first.setName(second.getName());
        first.setDisplayName(second.getDisplayName());
        return second;
    }

    private UserTypes convertDtoToEntity(UserTypesDto first, UserTypes second) {
        first.setId(second.getId());
        first.setName(second.getName());
        first.setDisplayName(second.getDisplayName());
        return second;
    }
}
