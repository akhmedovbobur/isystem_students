package com.example.isystem_students.service;

import com.example.isystem_students.dto.GroupTypesDto;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.GroupTypes;
import com.example.isystem_students.repository.GroupTypesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GroupTypesService {

    @Autowired
    private GroupTypesRepository groupTypesRepository;

    public GroupTypesDto get(Integer id){
        return convertEntityToDto(getGroupTypesEntity(id));
    }
    public  void create(GroupTypesDto groupTypesDto){
        GroupTypes groupTypes= convertDtoToEntity(groupTypesDto);
        groupTypes.setStatus(true);
        groupTypes.setCreatedAt(LocalDateTime.now());
        groupTypesRepository.save(groupTypes);
    }
    public void update(Integer id,
                       GroupTypesDto groupTypesDto){
        GroupTypes groupTypes=getGroupTypesEntity(id);
        groupTypes.setName(groupTypesDto.getName());
        groupTypes.setUpdatedAt(LocalDateTime.now());
        groupTypesRepository.save(groupTypes);
        }
    public void delete(Integer id){
        GroupTypes groupTypes=getGroupTypesEntity(id);
        groupTypes.setDeletedAt(LocalDateTime.now());
        groupTypesRepository.delete(groupTypes);
    }



    public static GroupTypes convertDtoToEntity(GroupTypesDto groupTypesDto) {
        return GroupTypes.builder()
                .id(groupTypesDto.getId())
                .name(groupTypesDto.getName())
                .status(groupTypesDto.getStatus())
                .build();
    }

    public static GroupTypesDto convertEntityToDto(GroupTypes groupTypes) {
        return GroupTypesDto.builder()
                .id(groupTypes.getId())
                .name(groupTypes.getName())
                .status(groupTypes.getStatus())
                .build();
    }

    public GroupTypes getGroupTypesEntity(Integer id){
        Optional<GroupTypes> optional = groupTypesRepository.findById(id);
        if (optional.isEmpty()){
            throw new ServerBadRequestException("Group types does not exist!");
        }
        return optional.get();
    }
}
