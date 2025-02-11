package com.learnsphere.repository;

import com.learnsphere.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
    Course findByCourseName(String courseName);
}
