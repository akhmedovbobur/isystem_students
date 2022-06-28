package com.example.isystem_students.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class GroupDto {

    private Integer id;

    @NotBlank
    private String name;

    private CourseDto courseDto;
    private Integer courseId;

    private GroupTypesDto groupTypesDto;
    private Integer groupTypesId;

    private Boolean status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
