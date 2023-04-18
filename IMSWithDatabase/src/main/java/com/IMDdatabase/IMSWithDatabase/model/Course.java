package com.IMDdatabase.IMSWithDatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Set;

/**
 * Represents a course entity in the database.
 */
@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int courseID;

    @Column
    public String name;

    @OneToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @JsonIgnore
    public Teacher mentor;

    /**
     * Gets the mentor's ID for the course.
     *
     * @return The mentor's ID or null if no mentor is assigned.
     */
    @JsonProperty("teacher_id")
    public Integer getMentorId() {
        return mentor != null ? mentor.id : null;
    }

    // Many-to-many relationship with Student
    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    public Set<Student> enrolledStudent;

}
