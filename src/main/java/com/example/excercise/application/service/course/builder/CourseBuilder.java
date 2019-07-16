package com.example.excercise.application.service.course.builder;

import com.example.excercise.application.service.course.dto.request.CreateCourseDto;
import com.example.excercise.domain.model.course.Course;
import com.example.excercise.domain.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CourseBuilder {

    CreateCourseDto createCourseDto;

    User user;

    Course course;

    public CourseBuilder buildOnCreate() {
        this.course = new Course(
                createCourseDto.getName(),
                createCourseDto.getStartDate(),
                createCourseDto.getEndDate(),
                createCourseDto.getCapacity(),
                user
        );

        return this;
    }

}
