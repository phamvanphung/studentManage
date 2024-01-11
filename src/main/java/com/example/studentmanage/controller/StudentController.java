package com.example.studentmanage.controller;


import com.example.studentmanage.common.ApiResponse;
import com.example.studentmanage.dto.student.request.CreateStudentRequest;
import com.example.studentmanage.dto.student.response.StudentResponse;
import com.example.studentmanage.services.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping(value = "/add")
    private ResponseEntity<ApiResponse<StudentResponse>> addStudent (@Validated @RequestBody CreateStudentRequest request){
        log.info("Has request add student with data: {}", request.toString());
        ApiResponse<StudentResponse> response = studentService.add(request);
        log.info("Has response add student with data: {}", response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
