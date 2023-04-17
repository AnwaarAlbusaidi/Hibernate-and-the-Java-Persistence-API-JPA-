package com.IMDdatabase.IMSWithDatabase.model;

import jakarta.persistence.*;

/**
 * Represents a teacher entity in the database.
 */
@Entity
@Table(name = "Teacher")
public class Teacher {

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

    /**
     * Gets the ID of the teacher.
     *
     * @return The ID of the teacher.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the teacher.
     *
     * @param id The ID of the teacher.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the teacher.
     *
     * @return The name of the teacher.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the teacher.
     *
     * @param name The name of the teacher.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the teacher.
     *
     * @return The email of the teacher.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the teacher.
     *
     * @param email The email of the teacher.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the salary of the teacher.
     *
     * @return The salary of the teacher.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets the salary of the teacher.
     *
     * @param salary The salary of the teacher.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
}
