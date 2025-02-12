package com.learnsphere.service;

import com.learnsphere.entity.Course;
import com.learnsphere.repository.CourseRepository;

import java.util.Collections;
import java.util.List;

public class CourseServiceImpl implements CourseService{

    CourseRepository courseRepository;
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    @Override
    public Course getCourse(int courseId) {
        return courseRepository.findById(courseId).get();
    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }

    @Override
    public List<Course> getCourseBySkill(String skillName) {
        return courseRepository.findAllBySkill(skillName);
    }

    @Override
    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(int courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Course getCourseByCourseId(int courseId) {
        return courseRepository.findById(courseId).get();
    }
}
