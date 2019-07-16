package com.example.excercise.infrastructure.springboot.controller;

import com.example.excercise.application.service.course.CreateCourseDayMemberService;
import com.example.excercise.application.service.course.CreateCourseService;
import com.example.excercise.application.service.course.dto.request.CreateCourseDayMemberDto;
import com.example.excercise.application.service.course.dto.request.CreateCourseDto;
import com.example.excercise.application.service.course.dto.response.CourseDayMemberDto;
import com.example.excercise.application.service.course.dto.response.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class CourseController {

    @Autowired
    private CreateCourseService createCourseService;

    @Autowired
    private CreateCourseDayMemberService createCourseDayMemberService;


    /**
     * Create a course
     *
     * @param createCourseDto
     */
    @RequestMapping(method = RequestMethod.POST, path = "/classes")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto createCourse(@RequestBody @Validated CreateCourseDto createCourseDto){
        return createCourseService.execute(createCourseDto);
    }

    /**
     * Book a course
     *
     * @param createCourseDayMemberDto
     */
    @RequestMapping(method = RequestMethod.POST, path = "/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDayMemberDto bookCourse(@RequestBody @Validated CreateCourseDayMemberDto createCourseDayMemberDto){
        return createCourseDayMemberService.execute(createCourseDayMemberDto);
    }
}
