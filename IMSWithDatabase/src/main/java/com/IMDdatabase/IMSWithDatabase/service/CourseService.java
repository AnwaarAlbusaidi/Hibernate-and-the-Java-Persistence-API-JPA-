package com.IMDdatabase.IMSWithDatabase.service;

import com.IMDdatabase.IMSWithDatabase.Repository.CourseRepository;
import com.IMDdatabase.IMSWithDatabase.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllCourses(){
        logger.info("Get all courses" );
        return courseRepository.findAll();
    }

    public Optional<Course> getSpecificCourse(int id){
        Optional<Course> foundCourse = courseRepository.findById(id);
        logger.info("Get course with id: " + id);
        return foundCourse;
    }

    public  Course rigisterCourse(Course course){
        courseRepository.save(course);
        logger.info("Register course with id: " + course.courseID);
        return course;
    }
    public Optional<Course> updateSpecificCourse(Course updateCourse, int id){
        Optional<Course> foundCourse = courseRepository.findById(id);

        foundCourse.ifPresent((currentCourse)->{
            currentCourse.name = updateCourse.name;
            currentCourse.enrolledStudent = updateCourse.enrolledStudent;
            courseRepository.save(currentCourse);
        });
        logger.info("updated course with id: " + id);
        return foundCourse;
    }

    public void dropSpecificCourse(int id){
        logger.info("Delete course with id: " + id);
        courseRepository.deleteById(id);
    }
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
}
