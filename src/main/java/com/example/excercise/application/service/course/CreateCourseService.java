package com.example.excercise.application.service.course;

import com.example.excercise.application.datatransformer.ModelToDtoTransformer;
import com.example.excercise.application.service.course.builder.CourseBuilder;
import com.example.excercise.application.service.course.dto.request.CreateCourseDto;
import com.example.excercise.application.service.course.dto.response.CourseDto;
import com.example.excercise.domain.model.course.Course;
import com.example.excercise.domain.model.course.CourseRepository;
import com.example.excercise.domain.model.exception.NotFoundException;
import com.example.excercise.domain.model.user.User;
import com.example.excercise.domain.model.user.UserRepository;
import com.example.excercise.domain.model.user.UserType;

import javax.transaction.Transactional;

@Transactional
public class CreateCourseService {

    UserRepository userRepository;

    CourseRepository courseRepository;

    ModelToDtoTransformer<Course, CourseDto> dataTrasofmer;

    CourseBuilder courseBuilder;

    public CreateCourseService(
            UserRepository userRepository,
            CourseRepository courseRepository,
            ModelToDtoTransformer<Course, CourseDto> dataTrasofmer,
            CourseBuilder courseBuilder
    ) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.dataTrasofmer = dataTrasofmer;
        this.courseBuilder = courseBuilder;
    }

    public CourseDto execute(CreateCourseDto dto) {
        User user = userRepository.findById(dto.getUserId());

        if (user == null) {
            throw new NotFoundException(String.format("User with id %d is not found", dto.getUserId()));
        }

        if(!user.getType().equals(UserType.OWNER.getName())){
            throw new IllegalArgumentException("This user type cannot create course");
        }

        Course course = this.courseBuilder
                .setUser(user)
                .setCreateCourseDto(dto)
                .buildOnCreate()
                .getCourse();

        this.courseRepository.save(course);

        this.dataTrasofmer.write(course);
        return dataTrasofmer.read(CourseDto.class);
    }
}
