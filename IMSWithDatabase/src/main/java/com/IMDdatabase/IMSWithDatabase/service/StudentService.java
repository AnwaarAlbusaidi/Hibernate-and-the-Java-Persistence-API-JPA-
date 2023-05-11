package com.IMDdatabase.IMSWithDatabase.service;

import com.IMDdatabase.IMSWithDatabase.Repository.StudentRepository;
import com.IMDdatabase.IMSWithDatabase.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Optional<Student> getSpecificStudent(int id ){
        logger.info("Het student with id: " + id);
        return studentRepository.findById(id);
    }
    public List<Student> getListOfStudentInfo(){
        logger.info("Get all Student");
        return studentRepository.findAll();
    }
    public Student registerStudent(Student student){
        studentRepository.save(student);
        logger.info("Created student with id: " + student.id);
        return student;
    }

    public  Optional<Student> deleteStudent(int id){
        Optional<Student> student = getSpecificStudent(id);
        studentRepository.deleteById(id);
        logger.info("Deleted student with id: " + id);
        return student;
    }

    public Optional<Student> updateStudentInfo(int id,Student upToDateStudent){
        Optional<Student> foundStudent = getSpecificStudent(id);
        foundStudent.ifPresent(
                (currentStudent) -> {
                    currentStudent.name = upToDateStudent.name;
                    currentStudent.email = upToDateStudent.email;

                    studentRepository.save(currentStudent);
                }
        );
        logger.info("updated student with id: " + id);
        return foundStudent;
    }

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
}
