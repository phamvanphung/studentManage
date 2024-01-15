package com.example.studentmanage.controller;


import com.example.studentmanage.common.ApiResponse;
import com.example.studentmanage.dto.course.request.AddCourseRequest;
import com.example.studentmanage.dto.course.request.GetListCourseRequest;
import com.example.studentmanage.dto.course.request.UpdateCourseRequest;
import com.example.studentmanage.dto.course.response.CourseResponse;
import com.example.studentmanage.dto.student.response.StudentResponse;
import com.example.studentmanage.services.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;


    //TODO: api add
    @PostMapping("/add")
    ResponseEntity<ApiResponse<CourseResponse>> addCourse(@Validated  @RequestBody AddCourseRequest request) {
        log.info("Has request add course with data {}", request.toString());
        CourseResponse course = courseService.addCourse(request);
        log.info("Has request add course with data : {}", course.toString());
        ApiResponse<CourseResponse> response = new ApiResponse<>().ok(course);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO: api update


    @PutMapping("/update")
    ResponseEntity<ApiResponse<CourseResponse>> updateCourse(@Validated  @RequestBody UpdateCourseRequest request) {
        log.info("Has request update course with data {}", request.toString());
        CourseResponse course = courseService.updateCourse(request);
        log.info("Has request update course with data : {}", course.toString());
        ApiResponse<CourseResponse> response = new ApiResponse<>().ok(course);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //TODO: api search

    @GetMapping("/list")
    ResponseEntity<ApiResponse<List<CourseResponse>>> getListCourse(@ParameterObject GetListCourseRequest request) {
        log.info("Has request get list course with keyword:  {}", request.toString());
        List<CourseResponse> course = courseService.getListCourse(request);
        log.info("Has request update course with data : {}", course.toString());
        ApiResponse<List<CourseResponse>> response = new ApiResponse<>().ok(course);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
