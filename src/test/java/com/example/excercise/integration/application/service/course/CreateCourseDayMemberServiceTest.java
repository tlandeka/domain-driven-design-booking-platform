package com.example.excercise.integration.application.service.course;

import com.example.excercise.application.service.course.CreateCourseDayMemberService;
import com.example.excercise.application.service.course.CreateCourseService;
import com.example.excercise.application.service.course.dto.request.CreateCourseDayMemberDto;
import com.example.excercise.application.service.course.dto.request.CreateCourseDto;
import com.example.excercise.application.service.course.dto.response.CourseDayMemberDto;
import com.example.excercise.application.service.course.dto.response.CourseDto;
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
import java.util.Date;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CreateCourseDayMemberServiceTest {
    @Autowired
    private CreateCourseDayMemberService createCourseDayMemberService;

    @Autowired
    private CreateCourseService createCourseService;

    @Autowired
    UserJpaRepository userJpaRepository;

    CourseDto courseDto;

    @Before
    public void setUp() {
        this.userJpaRepository.saveAll(UserObjectMother.getAllUsers());

        CreateCourseDto createDto = CourseDtoObjectMother.getCreateCourseDto();
        this.courseDto = this.createCourseService.execute(createDto);
    }

    @Test
    public void testWhenValidInputThenCreateCourseDayMember() {
        // Test adding the courseDayMember
        CreateCourseDayMemberDto createDto = CourseDtoObjectMother.getCreateCourseDayMemberDto();
        createDto.setCourseId(this.courseDto.getId());

        CourseDayMemberDto courseDayMemberDto = this.createCourseDayMemberService.execute(createDto);

        // Verify the addition
        assertNotNull(courseDayMemberDto);
        assertNotNull(courseDayMemberDto.getMessage());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenInvalidDateThenRejectCourseDayMemberCreation()  {
        CreateCourseDayMemberDto createDto = CourseDtoObjectMother.getCreateCourseDayMemberDto();
        createDto.setCourseId(this.courseDto.getId());
        createDto.setCourseDate(DateUtil.addDays(new Date(), CourseDtoObjectMother.PLUS_DAY + 3));

        this.createCourseDayMemberService.execute(createDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenCourseDayMemberAlreadyBookedThenRejectCourseDayMemberCreation()  {
        CreateCourseDayMemberDto createDto = CourseDtoObjectMother.getCreateCourseDayMemberDto();
        createDto.setCourseId(this.courseDto.getId());

        this.createCourseDayMemberService.execute(createDto);
        this.createCourseDayMemberService.execute(createDto);
    }

}
