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

@RestController
@RequestMapping(path = "/api/assigner")
public class CourseAssignerController {

    @Autowired
    CourseService courseService;
    @Autowired
    TeacherService teacherService;
    @PostMapping
    public CourseAssigner assignMentorToCourse(@RequestBody CourseAssigner courseAssigner){
        Optional<Course> optionalCourse= courseService.getSpecificCourse(courseAssigner.course_id);
        Optional<Teacher> optionalTeacher= teacherService.getSpecificTeacher(courseAssigner.teacher_id);

        optionalCourse.ifPresent((course)->{
            optionalTeacher.ifPresent((teacher)->{
                course.mentor= teacher;
                courseService.rigisterCourse(course);
            });
        });
        return courseAssigner;
    }
}
