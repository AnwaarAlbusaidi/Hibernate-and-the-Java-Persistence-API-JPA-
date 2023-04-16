package com.IMDdatabase.IMSWithDatabase.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Teacher")
public class Teacher{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column
    public String name;
    @Column
    public String email;
    @Column
    public double salary;

    @OneToOne(mappedBy = "mentor")
    public Course course;

}