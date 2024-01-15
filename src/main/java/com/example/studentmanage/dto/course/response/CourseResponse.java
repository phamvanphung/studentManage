package com.example.studentmanage.dto.course.response;


import com.example.studentmanage.entity.Course;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseResponse {
    private Long id;
    private String code;
    private String name;


    public CourseResponse(Course course) {
        this.id = course.getId();
        this.code = course.getCode();
        this.name = course.getName();
    }
}
