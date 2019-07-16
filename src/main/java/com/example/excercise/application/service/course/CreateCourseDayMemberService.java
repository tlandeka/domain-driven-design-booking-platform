package com.example.excercise.application.service.course;

import com.example.excercise.application.datatransformer.ModelToDtoTransformer;
import com.example.excercise.application.service.course.dto.factory.DtoFactory;
import com.example.excercise.application.service.course.dto.request.CreateCourseDayMemberDto;
import com.example.excercise.application.service.course.dto.response.CourseDayMemberDto;
import com.example.excercise.domain.model.course.Course;
import com.example.excercise.domain.model.course.CourseDayMember;
import com.example.excercise.domain.model.course.CourseRepository;
import com.example.excercise.domain.model.exception.NotFoundException;
import com.example.excercise.domain.model.user.User;
import com.example.excercise.domain.model.user.UserRepository;
import com.example.excercise.domain.model.user.UserType;

import javax.transaction.Transactional;

@Transactional
public class CreateCourseDayMemberService extends CourseService {

    private DtoFactory dtoFactory;

    public CreateCourseDayMemberService(
            UserRepository userRepository,
            CourseRepository courseRepository,
            DtoFactory dtoFactory
    ) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.dtoFactory = dtoFactory;
    }

    public CourseDayMemberDto execute(CreateCourseDayMemberDto dto) {
        User user = userRepository.findById(dto.getUserId());

        if(user == null){
            throw new NotFoundException(String.format("User with id %d is not found", dto.getUserId()));
        }

        if(!user.getType().equals(UserType.CLIENT.getName())){
            throw new IllegalArgumentException("This user type cannot book course");
        }

        Course course = this.courseRepository.findById(dto.getCourseId());

        if(course == null){
            throw new NotFoundException(String.format("Course with id %d is not found", dto.getCourseId()));
        }

        course.createCourseDayMember(user, dto.getCourseDate());
        this.courseRepository.save(course);

        CourseDayMemberDto courseDayMemberDto = this.dtoFactory.createCourseDayMemberDto();
        courseDayMemberDto.setMessage("Success!!");

        return courseDayMemberDto;
    }
}
