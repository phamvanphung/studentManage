package com.example.studentmanage.services;


import com.example.studentmanage.dto.course.response.CourseResponse;
import com.example.studentmanage.dto.grade.request.AddGradeRequest;
import com.example.studentmanage.dto.grade.request.GetListGradeRequest;
import com.example.studentmanage.dto.grade.request.GetListStudentGradeByCourseRequest;
import com.example.studentmanage.dto.grade.request.UpdateGradeRequest;
import com.example.studentmanage.dto.grade.response.GradeResponse;
import com.example.studentmanage.entity.Course;
import com.example.studentmanage.entity.Grade;
import com.example.studentmanage.entity.Student;
import com.example.studentmanage.enums.ResponseCode;
import com.example.studentmanage.exception.BusinessException;
import com.example.studentmanage.repository.CourseRepository;
import com.example.studentmanage.repository.GradeRepository;
import com.example.studentmanage.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;


    public GradeResponse addGrade(AddGradeRequest request){
        Student student = studentRepository.findById(request.getIdStudent()).orElse(null);
        if(Objects.isNull(student)) {
            throw new BusinessException(ResponseCode.STUDENT_NOT_FOUND);
        }

        Course course = courseRepository.findById(request.getIdCourse()).orElse(null);
        if(Objects.isNull(course)){
            throw new BusinessException(ResponseCode.COURSE_NOT_FOUND);
        }

        Grade grade = new Grade();
        grade.setGrade(request.getGrade());
        grade.setCourse(course);
        grade.setStudent(student);
        grade = gradeRepository.save(grade);
        return new GradeResponse(grade);
    }


    public GradeResponse updateGrade(UpdateGradeRequest request){

        Grade grade = gradeRepository.findById(request.getId()).orElse(null);
        if(Objects.isNull(grade)){
            throw new BusinessException(ResponseCode.GRADE_NOT_FOUND);
        }

        if(Objects.nonNull(request.getIdStudent()) && !request.getIdStudent().equals(0)){
            Student student = studentRepository.findById(request.getIdStudent()).orElse(null);
            if(Objects.isNull(student)) {
                throw new BusinessException(ResponseCode.STUDENT_NOT_FOUND);
            }
            grade.setStudent(student);
        }

        if(Objects.nonNull(request.getIdCourse()) && !request.getIdCourse().equals(0)){
            Course course = courseRepository.findById(request.getIdCourse()).orElse(null);
            if(Objects.isNull(course)){
                throw new BusinessException(ResponseCode.COURSE_NOT_FOUND);
            }
            grade.setCourse(course);
        }
        if(Objects.nonNull(request.getGrade())){
            grade.setGrade(request.getGrade());
        }
        grade = gradeRepository.save(grade);
        return new GradeResponse(grade);
    }



    public List<GradeResponse> getList(GetListGradeRequest request){
        if(StringUtils.isBlank(request.getKeyword())){
            request.setKeyword("");
        }

        String keyword = "%" + request.getKeyword() + "%";
        List<Grade> gradeResponseList = gradeRepository.listGrade(keyword);
        return gradeResponseList.stream().map(GradeResponse::new).collect(Collectors.toList());
    }


    public List<GradeResponse> getList(GetListStudentGradeByCourseRequest request){
        Course course = courseRepository.findById(request.getId()).orElse(null);
        List<Grade> gradeResponseList = gradeRepository.findAllByCourse(course);
        return gradeResponseList.stream().map(GradeResponse::new).collect(Collectors.toList());
    }
}
