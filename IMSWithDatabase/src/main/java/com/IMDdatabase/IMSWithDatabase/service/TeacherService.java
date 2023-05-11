package com.IMDdatabase.IMSWithDatabase.service;

import com.IMDdatabase.IMSWithDatabase.Repository.TeacherRepository;
import com.IMDdatabase.IMSWithDatabase.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public Optional<Teacher> getSpecificTeacher(int id ){
        logger.info("Get teacher with id: " + id);
        return teacherRepository.findById(id);
    }
    public List<Teacher> getTeacherInfo(){
        logger.info("Get all teacher" );
        return teacherRepository.findAll();
    }
    public Teacher hireTeacher(Teacher teacher ){
        teacherRepository.save(teacher);
        logger.info("Register teacher with id: " + teacher.id);
        return teacher;
    }

    public  Optional<Teacher> fireTeacher(int id){
        Optional<Teacher> teacher = getSpecificTeacher(id);
        teacherRepository.deleteById(id);
        logger.info("Delete teacher with id: " + id);
        return teacher;
    }

    public Optional<Teacher> updateTeacherInfo(int id,Teacher upToDateTeacher){
        Optional<Teacher> foundTeacher = getSpecificTeacher(id);
        foundTeacher.ifPresent(
                (currentTeacher) -> {
                    currentTeacher.name = upToDateTeacher.name;
                    currentTeacher.email = upToDateTeacher.email;
                    currentTeacher.salary = upToDateTeacher.salary;

                    teacherRepository.save(currentTeacher);
                }
        );
        logger.info("updated teacher with id: " + id);
        return foundTeacher;
    }

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
}
