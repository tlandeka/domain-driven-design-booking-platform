package com.example.excercise.testdatafactory.application.service.course.dto;

import com.example.excercise.application.service.course.dto.request.CreateCourseDayMemberDto;
import com.example.excercise.application.service.course.dto.request.CreateCourseDto;
import com.example.excercise.application.service.course.dto.response.CourseDto;
import com.example.excercise.domain.model.course.Course;
import com.example.excercise.testdatafactory.domain.model.course.CourseObjectMother;
import com.example.excercise.testdatafactory.domain.model.user.UserObjectMother;
import com.example.excercise.testdatafactory.utils.DateUtil;

import java.util.Date;

public class CourseDtoObjectMother {

    public static Integer PLUS_DAY = 3;

    public static CreateCourseDto getCreateCourseDto() {
        return new CreateCourseDto(
                UserObjectMother.getUserOwner().getId(),
                "Pilates",
                new Date(),
                DateUtil.addDays(new Date(), PLUS_DAY),
                3
        );
    }

    public static CreateCourseDayMemberDto getCreateCourseDayMemberDto() {
        return new CreateCourseDayMemberDto(
                UserObjectMother.getUserClient().getId(),
                1,
                DateUtil.addDays(new Date(),
                        PLUS_DAY - 1)
        );
    }

    public static CourseDto getCourseDto(){
        Course course = CourseObjectMother.getCourse();
        return new CourseDto(course.getId(), course.getName());
    }


}
