package com.example.excercise.application.service.course;

import com.example.excercise.domain.model.course.CourseRepository;
import com.example.excercise.domain.model.user.UserRepository;

public abstract class CourseService {
    protected UserRepository userRepository;

    protected CourseRepository courseRepository;
}
