package com.example.studentmanage.dto.course.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddCourseRequest {

    @NotBlank(message = "code course must be not null")
    private String code;

    @NotBlank(message = "name course must be not null")
    private String name;
}
