package com.example.studentmanage.dto.student.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentRequest {
    @NotNull(message = "Id phai truyen vao")
    private Long id;
    private String name;
    private String code;
    private String address;
}
