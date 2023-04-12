package com.IMDdatabase.IMSWithDatabase.controller;

import com.IMDdatabase.IMSWithDatabase.model.Course;
import com.IMDdatabase.IMSWithDatabase.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/courses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping(path="/{id}")
    public Optional<Course> getCourse(@PathVariable int id){
        return courseService.getSpecificCourse(id);
    }
    @PostMapping
    public Course registerCourse(@RequestBody Course course){
       courseService.rigisterCourse(course);
        return course;
    }

    @PutMapping(path="/{id}")
    public  Optional<Course> updateCourse(@PathVariable int id, @RequestBody  Course course){
        return courseService.updateSpecificCourse(course,id);
    }
    @DeleteMapping(path="/{id}")
    public void DeleteSpecificCourse(@PathVariable int id){
        courseService.dropSpecificCourse(id);
    }
}
