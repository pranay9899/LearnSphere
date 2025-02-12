package com.learnsphere.service;

import com.learnsphere.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<Student> getStudents();
    public Student addStudent(Student student);
    public Student updateStudent(Student student);
    public void deleteStudent(int id);
    public Student getStudentByName(String name);
    public Student getStudentByEmail(String email);
    public Student getStudentByPhone(String phone);
    public Student getStudentByID(int id);
    public List<Student> getStudentsByName(String name);
}
