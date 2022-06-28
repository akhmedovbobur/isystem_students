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

public class UserGroupsDto {

    private Integer id;

    private Boolean status;

    private GroupDto groupDto;
    private Integer groupId;

    private UserDto userDto;
    private Integer userId;

    private TeacherDto teacherDto;
    private Integer teacherId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
