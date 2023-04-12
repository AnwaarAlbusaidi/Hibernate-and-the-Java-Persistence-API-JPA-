package com.IMDdatabase.IMSWithDatabase.controller;

import com.IMDdatabase.IMSWithDatabase.model.Student;
import com.IMDdatabase.IMSWithDatabase.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/student")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping
    public List<Student> getAllStudent(){
        return studentService.getListOfStudentInfo();
    }

    @GetMapping(path="/{id}")
    public Optional<Student> getStudent(@PathVariable int id){
        return studentService.getSpecificStudent(id);
    }
    @PostMapping
    public Student registerStudent(@Valid @RequestBody Student student){
        studentService.registerStudnet(student);
        return student;
    }

    @PutMapping(path="/{id}")
    public  Optional<Student> updateStudent(@PathVariable int id, @RequestBody Student student){
        return studentService.updateStudentInfo(id,student);
    }
    @DeleteMapping(path="/{id}")
    public Optional<Student> DeleteStudent(@PathVariable int id){
        return studentService.deleteStudent(id);
    }
}
