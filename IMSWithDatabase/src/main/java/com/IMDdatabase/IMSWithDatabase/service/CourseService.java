package com.IMDdatabase.IMSWithDatabase.service;

import com.IMDdatabase.IMSWithDatabase.Repository.CourseRepository;
import com.IMDdatabase.IMSWithDatabase.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Optional<Course> getSpecificCourse(int id){
        Optional<Course> foundCourse = courseRepository.findById(id);
        return foundCourse;
    }

    public  Course rigisterCourse(Course course){
        courseRepository.save(course);
        return course;
    }
    public Optional<Course> updateSpecificCourse(Course updateCourse, int id){
        Optional<Course> foundCourse = courseRepository.findById(id);

        foundCourse.ifPresent((currentCourse)->{
            currentCourse.name = updateCourse.name;
            courseRepository.save(currentCourse);
        });

        return foundCourse;
    }

    public void dropSpecificCourse(int id){
        courseRepository.deleteById(id);
    }

}
