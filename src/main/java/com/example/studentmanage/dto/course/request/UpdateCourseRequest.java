package com.example.studentmanage.dto.course.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateCourseRequest {

    @NotNull(message = "id must not null")
    private Long id;
    
    private String code;
    private String name;
}
