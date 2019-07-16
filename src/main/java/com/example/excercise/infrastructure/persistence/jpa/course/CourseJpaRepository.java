package com.example.excercise.infrastructure.persistence.jpa.course;

import com.example.excercise.domain.model.course.Course;
import com.example.excercise.domain.model.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseJpaRepository implements CourseRepository {

    @Autowired
    CourseJpaRepositoryInterface crudRepository;

    @Override
    public Course save(Course course) {
        return this.crudRepository.save(course);
    }

    @Override
    public Course findById(Integer id) {
        Optional<Course> item = this.crudRepository.findById(id);
        if(!item.isPresent()){
            return null;
        }

        return item.get();
    }
}
