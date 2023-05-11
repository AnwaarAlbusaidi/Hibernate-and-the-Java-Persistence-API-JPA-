package com.IMDdatabase.IMSWithDatabase.controller;

import com.IMDdatabase.IMSWithDatabase.model.Course;
import com.IMDdatabase.IMSWithDatabase.model.Student;
import com.IMDdatabase.IMSWithDatabase.model.CourseEnrollment;
import com.IMDdatabase.IMSWithDatabase.service.CourseService;
import com.IMDdatabase.IMSWithDatabase.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
@RequestMapping(path = "/api/courseEnrollment")
public class CourseEnrollmentController {
    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;

    @PostMapping
    public void courseEnrollment(@RequestBody CourseEnrollment courseEnrollment)
    {
        Optional<Course> optionalCourse = courseService.getSpecificCourse(courseEnrollment.courseId);
        optionalCourse.ifPresentOrElse((course -> {
            courseEnrollment.studentId.forEach((currentStudentId)->{
                Optional<Student> optionalStudent = studentService.getSpecificStudent(currentStudentId);
                optionalStudent.ifPresent((student)->{
                    course.enrolledStudent.add(student);
                });
            });

            courseService.updateSpecificCourse(course,course.courseID);
        }),
        ()->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Course not Found");
        });
    }
}
