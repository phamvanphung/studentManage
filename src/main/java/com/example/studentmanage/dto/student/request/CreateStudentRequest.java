package com.example.studentmanage.dto.student.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateStudentRequest {

    @NotBlank
    private String fullname;
    @NotBlank
    private String code;
    @NotBlank
    private String address;
}
