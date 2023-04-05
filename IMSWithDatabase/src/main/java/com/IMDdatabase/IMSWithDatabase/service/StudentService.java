package com.IMDdatabase.IMSWithDatabase.service;

import com.IMDdatabase.IMSWithDatabase.Repositry.StudentRepository;
import com.IMDdatabase.IMSWithDatabase.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Optional<Student> getSpecificStudent(int id ){
        return studentRepository.findById(id);
    }
    public List<Student> getListOfStudentInfo(){
        return studentRepository.findAll();
    }
    public Student registerStudnet(Student student){
        studentRepository.save(student);
        return student;
    }

    public  Optional<Student> deleteStudent(int id){
        Optional<Student> student = getSpecificStudent(id);
        studentRepository.deleteById(id);
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
        return foundStudent;
    }
}
