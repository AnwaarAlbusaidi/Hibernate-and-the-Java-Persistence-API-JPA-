package com.IMDdatabase.IMSWithDatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int courseID;
    @Column
    public String name;

    @OneToOne(optional = true,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @JsonIgnore
    public Teacher mentor;

    @JsonProperty("teacher_id")
    public Integer getMentorId(){
        return mentor != null ? mentor.id : null;
    }

    //manyToMany relationship with Student
    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn (name = "course_id"),
            inverseJoinColumns = @JoinColumn(name= "student_id")
    )
    public Set<Student> enrolledStudent;
}
