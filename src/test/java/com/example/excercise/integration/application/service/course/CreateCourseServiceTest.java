package com.example.excercise.integration.application.service.course;

import com.example.excercise.application.service.course.CreateCourseService;
import com.example.excercise.application.service.course.dto.request.CreateCourseDto;
import com.example.excercise.application.service.course.dto.response.CourseDto;
import com.example.excercise.domain.model.course.Course;
import com.example.excercise.infrastructure.persistence.jpa.user.UserJpaRepository;
import com.example.excercise.testdatafactory.application.service.course.dto.CourseDtoObjectMother;
import com.example.excercise.testdatafactory.domain.model.user.UserObjectMother;
import com.example.excercise.testdatafactory.utils.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CreateCourseServiceTest {
    @Autowired
    private CreateCourseService createCourseService;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Before
    public void setUp() throws IOException {
        this.userJpaRepository.saveAll(UserObjectMother.getAllUsers());
    }

    @Test
    public void testWhenValidInputThenCreateCourse() {
        // Test adding the course
        CreateCourseDto createDto = CourseDtoObjectMother.getCreateCourseDto();
        CourseDto courseDto = this.createCourseService.execute(createDto);

        // Verify the addition
        assertNotNull(courseDto);
        assertNotNull(courseDto.getId());
        assertEquals(courseDto.getName(), createDto.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenInvalidDateThenRejectCourseCreation() {
        CreateCourseDto createDto = CourseDtoObjectMother.getCreateCourseDto();
        createDto.setStartDate(DateUtil.addDays(new Date(), CourseDtoObjectMother.PLUS_DAY + 3));
        this.createCourseService.execute(createDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenInvalidMaxCapacityThenRejectCourseCreation() {
        CreateCourseDto createDto = CourseDtoObjectMother.getCreateCourseDto();
        createDto.setCapacity(Course.MAX_CAPACITY + 1);
        this.createCourseService.execute(createDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenInvalidMinCapacityThenRejectCourseCreation() {
        CreateCourseDto createDto = CourseDtoObjectMother.getCreateCourseDto();
        createDto.setCapacity(Course.MIN_CAPACITY - 1);
        this.createCourseService.execute(createDto);
    }
}
