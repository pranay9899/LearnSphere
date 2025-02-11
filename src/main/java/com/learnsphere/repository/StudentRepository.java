package com.learnsphere.repository;

import com.learnsphere.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findAllByEmail(String email);
}
