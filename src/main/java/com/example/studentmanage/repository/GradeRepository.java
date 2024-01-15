package com.example.studentmanage.repository;

import com.example.studentmanage.entity.Course;
import com.example.studentmanage.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query(value = """
    SELECT * FROM Grade g 
    WHERE g.id like :keyword
""", nativeQuery = true)
    List<Grade> listGrade(String keyword);


    List<Grade> findAllByCourse (Course course);
}
