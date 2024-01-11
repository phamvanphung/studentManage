package com.example.studentmanage.services;


import com.example.studentmanage.common.ApiResponse;
import com.example.studentmanage.dto.student.request.CreateStudentRequest;
import com.example.studentmanage.dto.student.response.StudentResponse;
import com.example.studentmanage.entity.Student;
import com.example.studentmanage.enums.ResponseCode;
import com.example.studentmanage.exception.BusinessException;
import com.example.studentmanage.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentService {

    @Autowired
    private  StudentRepository studentRepository;

    public ApiResponse<StudentResponse> add (CreateStudentRequest request){
        Student student = studentRepository.findStudentByCode(request.getCode()).orElse(null);
        if(Objects.nonNull(student)){
            throw new BusinessException(ResponseCode.STUDENT_CODE_NOT_UNIQUE);
        }

        student = new Student();
        student.setAddress(request.getAddress());
        student.setFullName(request.getFullname());
        student.setCode(request.getCode());
        Student studentSave = studentRepository.save(student);
        return new ApiResponse<StudentResponse>().ok(new StudentResponse(studentSave));
    }


}
