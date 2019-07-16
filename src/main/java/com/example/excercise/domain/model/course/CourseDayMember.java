package com.example.excercise.domain.model.course;

import com.example.excercise.domain.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "class_day_member")
public class CourseDayMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_day_id")
    private CourseDay courseDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public CourseDayMember(CourseDay courseDay, User user) {
        this.setCourseDay(courseDay);
        this.setUser(user);
    }

    public void setCourseDay(CourseDay courseDay) {
        if(courseDay == null) throw new IllegalArgumentException("Course day cannot be null");
        this.courseDay = courseDay;
    }

    public void setUser(User user) {
        if(user == null) throw new IllegalArgumentException("User cannot be null");
        this.user = user;
    }
}
