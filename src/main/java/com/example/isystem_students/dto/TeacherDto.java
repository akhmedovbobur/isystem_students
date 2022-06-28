package com.example.isystem_students.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TeacherDto {

    private Integer id;

    private UserDto userDto;
    private Integer userId;

    private TeacherTypesDto teacherTypesDto;
    private Integer teacherTypesId;

    private Boolean status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


}
