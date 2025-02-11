package com.learnsphere.service;

import com.learnsphere.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    public Course getCourse(int courseId);
    public Course getCourseByName(String courseName);
    public List<Course> getCourseBySkill(String skillName);
    public List<Course> getAllCourses();
    public void saveCourse(Course course);
    public void deleteCourse(int courseId);
    public void updateCourse(Course course);
    public List<Course> getCourseByCourseName(String courseName);
    public List<Course> getCourseByCourseId(int courseId);
}
