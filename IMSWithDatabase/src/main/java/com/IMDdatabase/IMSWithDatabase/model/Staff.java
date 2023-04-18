package com.IMDdatabase.IMSWithDatabase.model;

import jakarta.persistence.*;

@Entity
@Table(name= "Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column
    public String name;
    @Column
    public String email;
    @Column
    public double salary;
}
