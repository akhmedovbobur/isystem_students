package com.example.isystem_students.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ClassesDto {

    private Integer id;

    @NotBlank
    private String name;

    private LocalDate date;

    private Boolean status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private RoomsDto roomsDto;
    private Integer roomsId;

    private UserGroupsDto userGroupsDto;
    private Integer userGroupsId;

    private AttendanceTypeDto attendanceTypeDto;
    private Integer attendanceTypeId;

}
