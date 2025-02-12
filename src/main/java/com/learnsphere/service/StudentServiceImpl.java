package com.learnsphere.service;

import com.learnsphere.entity.Student;
import com.learnsphere.repository.StudentRepository;

import java.util.List;

public class StudentServiceImpl implements StudentService{
    StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student getStudentByName(String name) {
        return studentRepository.findByFirstName(name);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Student getStudentByPhone(String phone) {
        return studentRepository.findByPhoneNumber(phone);
    }

    @Override
    public Student getStudentByID(int id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public List<Student> getStudentsByName(String name) {
        return studentRepository.findAllByFirstName(name);
    }
}
