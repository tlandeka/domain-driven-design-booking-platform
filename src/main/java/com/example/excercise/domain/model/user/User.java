package com.example.excercise.domain.model.user;

import com.example.excercise.domain.model.course.CourseDayMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Users")
@Table(name = "users")
public class User {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<CourseDayMember> courseDayMembers;

    public User(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
