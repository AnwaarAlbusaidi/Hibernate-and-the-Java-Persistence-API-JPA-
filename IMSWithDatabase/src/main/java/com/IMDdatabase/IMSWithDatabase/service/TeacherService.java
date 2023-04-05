package com.IMDdatabase.IMSWithDatabase.service;

import com.IMDdatabase.IMSWithDatabase.Repositry.TeacherRepository;
import com.IMDdatabase.IMSWithDatabase.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public Optional <Teacher> getSpecificTeacher(int id ){
        return teacherRepository.findById(id);
    }
    public List<Teacher> getTeacherInfo(){

        return teacherRepository.findAll();
    }
    public Teacher hireTeacher(Teacher teacher ){
        teacherRepository.save(teacher);
        return teacher;
    }

    public  Optional<Teacher> fireTeacher(int id){
        Optional<Teacher> teacher = getSpecificTeacher(id);
       teacherRepository.deleteById(id);
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
        return foundTeacher;
    }
}
