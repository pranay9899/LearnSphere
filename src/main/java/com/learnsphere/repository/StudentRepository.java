package com.learnsphere.repository;

import com.learnsphere.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByFirstName(String name);

    Student findByEmail(String email);

    Student findByPhoneNumber(String phoneNumber);

    List<Student> findAllByFirstName(String firstName);
}
