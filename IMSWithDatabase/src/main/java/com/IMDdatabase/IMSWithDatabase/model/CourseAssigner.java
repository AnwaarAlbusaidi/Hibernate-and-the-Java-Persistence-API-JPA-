package com.IMDdatabase.IMSWithDatabase.model;

/**
 * Model class representing the CourseAssigner, which contains information for assigning a mentor to a course.
 */
public class CourseAssigner {

    public int course_id;
    public int teacher_id;

    /**
     * Get the course ID.
     *
     * @return The course ID.
     */
    public int getCourseId() {
        return this.course_id;
    }

    /**
     * Get the teacher ID.
     *
     * @return The teacher ID.
     */
    public int getTeacherId() {
        return this.teacher_id;
    }
}

