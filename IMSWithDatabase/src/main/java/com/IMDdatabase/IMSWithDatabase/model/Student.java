package com.IMDdatabase.IMSWithDatabase.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column
    public String name;
    @Column
    @Pattern(regexp = "^[a-z|A-Z|.]+@+[a-z|A-Z]+\\.[a-z|A-Z]{2,}$")
    public String email;

    @Column
    public String StudentImage;

    @ManyToMany(mappedBy = "enrolledStudent")
    public Set<Course> registerCourses;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getStudentImage() {
        return StudentImage;
    }
}