package com.example.studentmanage.services;


import com.example.studentmanage.dto.course.request.AddCourseRequest;
import com.example.studentmanage.dto.course.request.GetListCourseRequest;
import com.example.studentmanage.dto.course.request.UpdateCourseRequest;
import com.example.studentmanage.dto.course.response.CourseResponse;
import com.example.studentmanage.entity.Course;
import com.example.studentmanage.enums.ResponseCode;
import com.example.studentmanage.exception.BusinessException;
import com.example.studentmanage.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public CourseResponse addCourse(AddCourseRequest request) {
        Course course = courseRepository.findByCode(request.getCode()).orElse(null);
        if(Objects.nonNull(course)){
            throw new BusinessException(ResponseCode.COURSE_CODE_NOT_UNIQUE);
        }
        course = new Course();
        course.setCode(request.getCode());
        course.setName(request.getName());
        course = courseRepository.save(course);
        return new CourseResponse(course);
    }


    public CourseResponse updateCourse(UpdateCourseRequest request) {

        Course course = courseRepository.findById(request.getId()).orElse(null);
        if(Objects.isNull(course)) {
            throw new BusinessException(ResponseCode.COURSE_NOT_FOUND);
        }

        if(StringUtils.isNotBlank(request.getCode())){
            Course courseFind = courseRepository.findByCode(request.getCode()).orElse(null);
            if(Objects.nonNull(courseFind) && !courseFind.getId().equals(course.getId())){
                throw new BusinessException(ResponseCode.COURSE_CODE_NOT_UNIQUE);
            }
            course.setCode(request.getCode());
        }

        if(StringUtils.isNotBlank(request.getName())){
            course.setName(request.getName());
        }
        course = courseRepository.save(course);
        return new CourseResponse(course);
    }



    public List<CourseResponse> getListCourse(GetListCourseRequest request){
        if(StringUtils.isBlank(request.getKeyword())){
            request.setKeyword("");
        }

        String keyword = "%" + request.getKeyword() + "%";
        List<Course> courseList = courseRepository.getListCourse(keyword);
        return courseList.stream().map(CourseResponse::new).collect(Collectors.toList());
    }





}
