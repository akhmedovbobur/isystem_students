package com.example.isystem_students.service;

import com.example.isystem_students.dto.RoomsDto;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.Rooms;
import com.example.isystem_students.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RoomsService {

    @Autowired
    private RoomRepository roomRepository;

    public RoomsDto get(Integer id){
        return convertEntityToDto(getRoomsEntity(id));
    }

    public  void  create(RoomsDto roomsDto){
        Rooms rooms =convertDtoToEntity(roomsDto);
        rooms.setStatus(true);
        rooms.setCreatedAt(LocalDateTime.now());
        roomRepository.save(rooms);
    }

    public void update(Integer id,
                       RoomsDto roomsDto){
        Rooms rooms =getRoomsEntity(id);
        rooms.setName(roomsDto.getName());
        rooms.setUpdatedAt(LocalDateTime.now());
        roomRepository.save(rooms);
    }

    public void delete(Integer id){
        Rooms rooms = getRoomsEntity(id);
        rooms.setDeletedAt(LocalDateTime.now());
        roomRepository.delete(rooms);
    }

    public Rooms getRoomsEntity(Integer id){
        Optional<Rooms> optional = roomRepository.findById(id);
        if (optional.isEmpty()  || optional.get().getDeletedAt() != null || !optional.get().getStatus()){
            throw  new ServerBadRequestException("Rooms does not exist!");
        }
        return optional.get();
    }
    public  RoomsDto convertEntityToDto(Rooms rooms){
        return RoomsDto.builder()
                .id(rooms.getId())
                .name(rooms.getName())
                .status(rooms.getStatus())
                .build();
    }
    public   Rooms convertDtoToEntity(RoomsDto roomsDto){
        return Rooms.builder()
                .id(roomsDto.getId())
                .name(roomsDto.getName())
                .status(roomsDto.getStatus())
                .build();
    }
}
