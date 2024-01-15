package com.example.studentmanage.repository;

import com.example.studentmanage.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCode(String code);


    @Query(
        value = """
        SELECT * FROM Course c
        WHERE c.id like :keyword
        OR LOWER(c.code) like LOWER(:keyword)
        OR LOWER(c.name) like LOWER(:keyword)
        ORDER BY c.id ASC
""",nativeQuery = true)
    List<Course> getListCourse(String keyword);
}
