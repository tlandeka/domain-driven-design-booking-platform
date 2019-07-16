package com.example.excercise.domain.model.course;

import com.example.excercise.domain.model.user.User;
import com.example.excercise.domain.model.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "class")
public class Course {

    public static final Integer MAX_CAPACITY = 5;
    public static final Integer MIN_CAPACITY = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    Set<CourseDay> courseDays;

    public Course(String name, Date startDate, Date endDate, Integer capacity, User user) {
        this.setName(name);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setCapacity(capacity);
        this.setUser(user);
        this.createCourseDays();
    }

    public void setName(String name) {
        if(name == null) throw new IllegalArgumentException("Name is null");
        if(name.isEmpty()) throw new IllegalArgumentException("Name is empty");
        this.name = name;
    }

    public void setStartDate(Date startDate) {
        if (startDate == null) throw new IllegalArgumentException("Start Date is null");
        if(this.endDate != null && this.endDate.before(startDate)) throw new IllegalArgumentException("End date is greater than start date");
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        if (endDate == null) throw new IllegalArgumentException("End Date is null");
        if(this.startDate != null && startDate.after(endDate)) throw new IllegalArgumentException("Start date is not greater than end date");
        this.endDate = endDate;
    }

    public void setCapacity(Integer capacity) {
        if(capacity > MAX_CAPACITY || capacity < MIN_CAPACITY){
            throw new IllegalArgumentException(String.format("Invalid capacity value. Min is %d and Max is %d", MIN_CAPACITY, MAX_CAPACITY));
        }
        this.capacity = capacity;
    }

    public void setUser(User user) {
        if(user == null) throw new IllegalArgumentException("User is null");
        this.user = user;
    }

    private void createCourseDays() {
        this.courseDays = new HashSet<>();
        Date current = this.getStartDate();

        while (current.before(this.getEndDate())) {
            this.createCourseDay(current);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(current);
            calendar.add(Calendar.DATE, 1);
            current = calendar.getTime();
        }

    }

    public CourseDay createCourseDay(Date date){
        CourseDay courseDay = new CourseDay(date, this);
        this.courseDays.add(courseDay);

        return courseDay;
    }


    public CourseDayMember createCourseDayMember(User user, Date date){
        CourseDay courseDay = this.getCourseDay(date);

        if(courseDay.getCourseDayMembers().size() >= this.capacity){
            throw new IllegalArgumentException("Capacity is exceeded");
        }

        return courseDay.createCourseDayMember(user);
    }

    private CourseDay getCourseDay(Date date){
        if(this.getCourseDays() == null) throw new IllegalArgumentException("Course day is not created");

        for (CourseDay courseDay : this.courseDays) {
            if(DateUtils.compareTwoDates(courseDay.getDate(), date) == 0) return courseDay;
        }

        throw new IllegalArgumentException(String.format("Course day for date %s is not exists", date.toString()));
    }

}
