package com.example.excercise.unit.application.service.course;

import com.example.excercise.application.datatransformer.ModelToDtoTransformer;
import com.example.excercise.application.service.course.CreateCourseService;
import com.example.excercise.application.service.course.builder.CourseBuilder;
import com.example.excercise.application.service.course.dto.request.CreateCourseDto;
import com.example.excercise.application.service.course.dto.response.CourseDto;
import com.example.excercise.domain.model.course.Course;
import com.example.excercise.domain.model.user.User;
import com.example.excercise.infrastructure.persistence.jpa.course.CourseJpaRepository;
import com.example.excercise.infrastructure.persistence.jpa.user.UserJpaRepository;
import com.example.excercise.testdatafactory.application.service.course.dto.CourseDtoObjectMother;
import com.example.excercise.testdatafactory.domain.model.course.CourseObjectMother;
import com.example.excercise.testdatafactory.domain.model.user.UserObjectMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import javax.transaction.Transactional;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CreateCourseTest {

    @Mock
    private CourseJpaRepository courseJpaRepository;

    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private CourseBuilder courseBuilder;

    @Mock
    private ModelToDtoTransformer<Course, CourseDto> modelToDtoTransformer;

    @InjectMocks
    private CreateCourseService createCourseService;

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWhenVaildInputThenCreateCourse() {
        CreateCourseDto mCreateCourseDto = CourseDtoObjectMother.getCreateCourseDto();
        CourseDto mCourseDto = CourseDtoObjectMother.getCourseDto();
        User mUser = UserObjectMother.getUserOwner();
        Course mCourse = CourseObjectMother.getCourse();

        when(userJpaRepository.findById(any(Integer.class))).thenReturn(mUser);
        when(courseJpaRepository.save(any(Course.class))).thenReturn(mCourse);

        when(modelToDtoTransformer.read(CourseDto.class)).thenReturn(mCourseDto);

        when(courseBuilder.setUser(any())).thenReturn(courseBuilder);
        when(courseBuilder.setCreateCourseDto(any())).thenReturn(courseBuilder);
        when(courseBuilder.buildOnCreate()).thenReturn(courseBuilder);
        when(courseBuilder.getCourse()).thenReturn(mCourse);

        CourseDto courseDto = createCourseService.execute(mCreateCourseDto);

        // Verify the save
        assertEquals(courseDto.getId(), mCourse.getId());
    }
}
