package com.example.excercise.testdatafactory.domain.model.course;

import com.example.excercise.application.service.course.builder.CourseBuilder;
import com.example.excercise.domain.model.course.Course;
import com.example.excercise.testdatafactory.application.service.course.dto.CourseDtoObjectMother;
import com.example.excercise.testdatafactory.domain.model.user.UserObjectMother;

public class CourseObjectMother {

    public static Integer COURSE_ID = 1;

    public static Course getCourse() {
        Course course = new CourseBuilder()
                .setUser(UserObjectMother.getUserOwner())
                .setCreateCourseDto(CourseDtoObjectMother.getCreateCourseDto())
                .buildOnCreate()
                .getCourse();

        //course.setId(COURSE_ID);

        return course;
    }
}
