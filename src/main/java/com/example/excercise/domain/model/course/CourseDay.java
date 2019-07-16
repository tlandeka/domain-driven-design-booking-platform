package com.example.excercise.domain.model.course;

import com.example.excercise.domain.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "class_day")
public class CourseDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "class_day")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Course course;

    @OneToMany(mappedBy = "courseDay", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<CourseDayMember> courseDayMembers;

    public CourseDay(Date date, Course course) {
        this.setDate(date);
        this.setCourse(course);
    }

    public void setDate(Date date) {
        if(date == null) throw new IllegalArgumentException("Class date cannot be null");
        this.date = date;
    }

    public void setCourse(Course course) {
        if(course == null) throw new IllegalArgumentException("Course cannot be null");
        this.course = course;
    }

    public CourseDayMember createCourseDayMember(User user) {
        for (CourseDayMember cdm : this.courseDayMembers) {
            if(cdm.getUser().getId() == user.getId()){
                throw new IllegalArgumentException(String.format("User %s have already booked this date", user.getName()));
            }
        }

        CourseDayMember courseDayMember = new CourseDayMember(this, user);
        this.courseDayMembers.add(courseDayMember);

        return courseDayMember;
    }

    public Set<CourseDayMember> getCourseDayMembers() {
        if(this.courseDayMembers == null) {
            this.courseDayMembers = new HashSet<>();
        }

        return courseDayMembers;
    }
}
