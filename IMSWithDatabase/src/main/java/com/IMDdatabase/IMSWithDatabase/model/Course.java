package com.IMDdatabase.IMSWithDatabase.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int courseID;
    @Column
    public String name;
}
