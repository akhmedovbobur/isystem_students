package com.example.isystem_students.dto;

import com.example.isystem_students.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString

public class UserDto {

    private  Integer id;

    private UserTypesDto userTypesDto;
    private Integer userTypeId;

    private UserImageDto userImageDto;
    private Integer userImageId;

    @NotBlank
    @Size(min=12, max=13)
    private String phone;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private Gender gender;

    private String firstName;
    private String lastName;
    private String middleName;

    private String addressFirst;
    private String addressSecond;
    private Boolean status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


}
