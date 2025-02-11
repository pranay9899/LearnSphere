package com.learnsphere.service;

import com.learnsphere.entity.Course;
import com.learnsphere.repository.CourseRepository;

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
        return List.of();
    }

    @Override
    public List<Course> getAllCourses() {
        return List.of();
    }

    @Override
    public void saveCourse(Course course) {

    }

    @Override
    public void deleteCourse(int courseId) {

    }

    @Override
    public void updateCourse(Course course) {

    }

    @Override
    public List<Course> getCourseByCourseName(String courseName) {
        return List.of();
    }

    @Override
    public List<Course> getCourseByCourseId(int courseId) {
        return List.of();
    }
}
