package com.IMDdatabase.IMSWithDatabase.model;

import jakarta.persistence.*;
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column
    public String name;
    @Column
    public String email;
}