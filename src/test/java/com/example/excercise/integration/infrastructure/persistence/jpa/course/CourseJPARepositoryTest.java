package com.example.excercise.integration.infrastructure.persistence.jpa.course;

import com.example.excercise.domain.model.course.Course;
import com.example.excercise.domain.model.user.UserRepository;
import com.example.excercise.infrastructure.persistence.jpa.course.CourseJpaRepository;
import com.example.excercise.infrastructure.persistence.jpa.user.UserJpaRepository;
import com.example.excercise.testdatafactory.domain.model.course.CourseObjectMother;
import com.example.excercise.testdatafactory.domain.model.user.UserObjectMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CourseJPARepositoryTest {
    @Autowired
    private CourseJpaRepository courseJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Before
    public void setUp() {
        this.userJpaRepository.saveAll(UserObjectMother.getAllUsers());
    }

    @Test
    public void testFindById() {
        Course aCourse = CourseObjectMother.getCourse();
        courseJpaRepository.save(aCourse);

        Course course = courseJpaRepository.findById(aCourse.getId());

        assertThat(aCourse.getId(), is(equalTo(course.getId())));
    }
}
