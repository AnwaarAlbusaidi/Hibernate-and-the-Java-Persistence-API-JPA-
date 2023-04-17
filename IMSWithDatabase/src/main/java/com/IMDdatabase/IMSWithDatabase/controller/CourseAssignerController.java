package com.IMDdatabase.IMSWithDatabase.controller;

import com.IMDdatabase.IMSWithDatabase.model.Course;
import com.IMDdatabase.IMSWithDatabase.model.CourseAssigner;
import com.IMDdatabase.IMSWithDatabase.model.Teacher;
import com.IMDdatabase.IMSWithDatabase.service.CourseService;
import com.IMDdatabase.IMSWithDatabase.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Controller class for assigning a mentor to a course.
 */
@RestController
@RequestMapping(path = "/api/assigner")
public class CourseAssignerController {

    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;

    /**
     * Endpoint for assigning a mentor to a course.
     *
     * @param courseAssigner The CourseAssigner object containing the course and teacher information.
     * @return The CourseAssigner object with updated mentor information.
     */
    @PostMapping
    public CourseAssigner assignMentorToCourse(@RequestBody CourseAssigner courseAssigner) {
        Optional<Course> optionalCourse = courseService.getSpecificCourse(courseAssigner.course_id);
        Optional<Teacher> optionalTeacher = teacherService.getSpecificTeacher(courseAssigner.teacher_id);

        optionalCourse.ifPresent((course) -> {
            optionalTeacher.ifPresent((teacher) -> {
                course.mentor= teacher;
                courseService.rigisterCourse(course);
            });
        });

        return courseAssigner;
    }
}

