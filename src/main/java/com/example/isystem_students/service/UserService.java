package com.example.isystem_students.service;

import com.example.isystem_students.dto.UserDto;
import com.example.isystem_students.enums.Roles;
import com.example.isystem_students.exception.ServerBadRequestException;
import com.example.isystem_students.model.User;
import com.example.isystem_students.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTypesService userTypesService;

    @Autowired
    private UserImageService userImageService;

    public UserDto get(Integer id){
        User user= getUserEntity(id);
        UserDto userDto = new UserDto();
        convertEntityToDto(user,userDto);
        userDto.setUserTypesDto(userTypesService.get(userDto.getUserTypeId()));
        userDto.setUserImageDto(userImageService.get(userDto.getUserImageId()));
        log.info("Get User Info {}", userDto);
        return userDto;
    }

    public UserDto create(UserDto userDto){
        Optional< User> optional =userRepository.findByEmailOrFirstName(userDto.getFirstName(), userDto.getEmail());
        if (optional.isPresent()){
            throw new ServerBadRequestException(" User with this email or username already exist ! ");
        }
        User user= new User();
        userTypesService.getUserTypeEntity(userDto.getUserTypeId());
        user.setUserImage(userImageService.getEntity(userDto.getUserImageId()));
        convertDtoToEntity(userDto, user);
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        log.info("Create User {}", user);
        return userDto;
    }

   public UserDto update(Integer id, UserDto userDto) {
       User user = getUserEntity(id);
       user.setId(id);
       // Because this data is unique
       user.setUserTypes(userTypesService.getUserTypeEntity(userDto.getUserTypeId()));
       user.setUpdatedAt(LocalDateTime.now());
       convertEntityToDto(user,userDto);
       userRepository.save(user);
       log.info("Update User {}", user);
       return userDto;
   }

    public Boolean delete(Integer id) {
        User user = getUserEntity(id);
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
        log.info("Delete User {}", user);
        return true;
    }

    public User getUserEntity(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty() || optional.get().getDeletedAt() != null) {
            throw new ServerBadRequestException("User does not exist !");
        }
        return optional.get();
    }


    public User convertEntityToDto(User first, UserDto second){
        first.setFirstName(second.getFirstName());
        first.setLastName(second.getLastName());
        first.setMiddleName(second.getMiddleName());
        first.setEmail(second.getEmail());
        first.setGender(second.getGender());
        first.setPassword(second.getPassword());
        first.setUserTypesId(second.getUserTypeId());
        first.setUserImageId(second.getUserImageId());
        first.setAddressFirst(second.getAddressFirst());
        first.setAddressSecond(second.getAddressSecond());
        first.setPhone(second.getPhone());
        return first;
    }


    public void convertDtoToEntity(UserDto first, User second){
        first.setId(second.getId());
        first.setFirstName(second.getFirstName());
        first.setLastName(second.getLastName());
        first.setMiddleName(second.getMiddleName());
        first.setGender(second.getGender());
        first.setUserImageId(second.getUserImageId());
        first.setUserTypeId(second.getUserTypesId());
        first.setPassword(second.getPassword());
        first.setPhone(second.getPhone());
        first.setEmail(second.getEmail());
        first.setAddressFirst(second.getAddressFirst());
        first.setAddressSecond(second.getAddressSecond());

    }
    public boolean changeUserToAdmin(Integer id) {
        User users = getUserEntity(id);
        users.setUserTypesId(Roles.ROLE_ADMIN.ordinal());
        users.setUpdatedAt(LocalDateTime.now());
        userRepository.save(users);
        return true;
    }
}
