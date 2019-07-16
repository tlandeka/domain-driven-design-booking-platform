package com.example.excercise.infrastructure.springboot.configuration;

import com.example.excercise.application.datatransformer.ModelToDtoTransformer;
import com.example.excercise.application.service.course.CreateCourseDayMemberService;
import com.example.excercise.application.service.course.CreateCourseService;
import com.example.excercise.application.service.course.builder.CourseBuilder;
import com.example.excercise.application.service.course.dto.factory.DtoFactory;
import com.example.excercise.infrastructure.persistence.jpa.course.CourseJpaRepository;
import com.example.excercise.infrastructure.persistence.jpa.user.UserJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfiguredBeans {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    CourseJpaRepository courseJpaRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CreateCourseService createCourseService(){
        return new CreateCourseService(userJpaRepository, courseJpaRepository, new ModelToDtoTransformer<>(new ModelMapper()), new CourseBuilder());
    }

    @Bean
    public CreateCourseDayMemberService createCourseDayMemberService(){
        return new CreateCourseDayMemberService(userJpaRepository, courseJpaRepository, new DtoFactory());
    }
}