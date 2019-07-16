package com.example.excercise.domain.model.course;

public interface CourseRepository {
    Course save(Course course);
    Course findById(Integer id);
}
