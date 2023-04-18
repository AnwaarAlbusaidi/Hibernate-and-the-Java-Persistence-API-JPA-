package com.IMDdatabase.IMSWithDatabase.controller;

import com.IMDdatabase.IMSWithDatabase.service.CourseService;
import com.IMDdatabase.IMSWithDatabase.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/courseEnrollment")
public class CourseEnrollment {
    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;

    @PostMapping
    public void courseEnrollment(@RequestBody CourseEnrollment courseEnrollment)
    {

    }
}
