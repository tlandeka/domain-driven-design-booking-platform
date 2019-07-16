package com.example.excercise.infrastructure.persistence.jpa.course;

import com.example.excercise.domain.model.course.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseJpaRepositoryInterface extends CrudRepository<Course, Integer> {
    Optional<Course> findById(Integer id);
    Course save(Course id);
}
