package com.example.studentmanage.controller;


import com.example.studentmanage.common.ApiResponse;
import com.example.studentmanage.dto.grade.request.AddGradeRequest;
import com.example.studentmanage.dto.grade.request.GetListGradeRequest;
import com.example.studentmanage.dto.grade.request.GetListStudentGradeByCourseRequest;
import com.example.studentmanage.dto.grade.request.UpdateGradeRequest;
import com.example.studentmanage.dto.grade.response.GradeResponse;
import com.example.studentmanage.services.GradeService;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/grade")
@Slf4j
public class GradeController {

    @Autowired
    private GradeService gradeService;

    //TODO: api add
    @PostMapping("/add")
    ResponseEntity<ApiResponse<GradeResponse>> addGrade(@Validated @RequestBody AddGradeRequest request) {
        log.info("Has request add grade with data {}", request.toString());
        GradeResponse grade = gradeService.addGrade(request);
        log.info("Has request add grade with data : {}", grade.toString());
        ApiResponse<GradeResponse> response = new ApiResponse<>().ok(grade);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO: api update


    @PutMapping("/update")
    ResponseEntity<ApiResponse<GradeResponse>> updateGrade(@Validated @RequestBody UpdateGradeRequest request) {
        log.info("Has request update grade with data {}", request.toString());
        GradeResponse gradeResponse = gradeService.updateGrade(request);
        log.info("Has request update grade with data : {}", gradeResponse.toString());
        ApiResponse<GradeResponse> response = new ApiResponse<>().ok(gradeResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //TODO: api search

    @GetMapping("/list")
    ResponseEntity<ApiResponse<List<GradeResponse>>> getListGrade(@ParameterObject GetListGradeRequest request) {
        log.info("Has request get list grade with keyword:  {}", request.toString());
        List<GradeResponse> gradeResponseList = gradeService.getList(request);
        log.info("Has request update grade with data : {}", gradeResponseList.toString());
        ApiResponse<List<GradeResponse>> response = new ApiResponse<>().ok(gradeResponseList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO: api search course

    @GetMapping("/course")
    ResponseEntity<ApiResponse<List<GradeResponse>>> getListGradeByCourse(@ParameterObject GetListStudentGradeByCourseRequest request) {
        log.info("Has request get list grade with id:  {}", request.toString());
        List<GradeResponse> gradeResponseList = gradeService.getList(request);
        log.info("Has request update grade with data : {}", gradeResponseList.toString());
        ApiResponse<List<GradeResponse>> response = new ApiResponse<>().ok(gradeResponseList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
