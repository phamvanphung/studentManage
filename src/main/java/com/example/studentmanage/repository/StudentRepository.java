package com.example.studentmanage.repository;

import com.example.studentmanage.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentByCode(String code);
}
