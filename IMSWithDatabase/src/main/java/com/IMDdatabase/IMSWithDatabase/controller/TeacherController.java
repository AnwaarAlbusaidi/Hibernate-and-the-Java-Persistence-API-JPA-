package com.IMDdatabase.IMSWithDatabase.controller;

import com.IMDdatabase.IMSWithDatabase.model.Teacher;
import com.IMDdatabase.IMSWithDatabase.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping
    public List<Teacher> getTeacher(){
        return teacherService.getTeacherInfo();
    }

    @GetMapping(path="/{id}")
    public  Optional<Teacher> getTeacher(@PathVariable int id){
        return teacherService.getSpecificTeacher(id);
    }
    @PostMapping
    public Teacher registerTeacher(@RequestBody Teacher teacher){
       teacherService.hireTeacher(teacher);
       return teacher;
    }

    @PutMapping(path="/{id}")
    public  Optional<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher teacher){
        return teacherService.updateTeacherInfo(id,teacher);
    }
    @DeleteMapping(path="/{id}")
    public Optional<Teacher> DeleteTeacher(@PathVariable int id){
        return teacherService.fireTeacher(id);
    }
}
