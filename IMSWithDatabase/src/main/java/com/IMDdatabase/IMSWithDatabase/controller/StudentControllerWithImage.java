package com.IMDdatabase.IMSWithDatabase.controller;

import com.IMDdatabase.IMSWithDatabase.model.Student;
import com.IMDdatabase.IMSWithDatabase.service.StudentService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/student/withImage")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentControllerWithImage {
    @Autowired
    StudentService studentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Student registerStudent(@RequestParam String name,
                                   @RequestParam String email,
                                   @RequestParam (required = false) MultipartFile image) throws IOException {
        Student newStudent = new Student();
        newStudent.name = name;
        newStudent.email = email;
        Student saveStudent = studentService.registerStudent(newStudent);
        if (image != null) {
            saveStudent.StudentImage = Integer.toString(saveStudent.getId()) + "_" + saveStudent.getName() + ".jpg";
            FileUtils.writeByteArrayToFile(new File("./src/main/resources/static/Student_images/" + saveStudent.getStudentImage()), image.getBytes());
            studentService.updateStudentInfo(saveStudent.getId(),saveStudent);
        }
        return saveStudent;
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<byte[]> getStudentWithImage(@PathVariable int id) throws IOException {
        Optional<Student> optionalStudent = studentService.getSpecificStudent(id);
        if (optionalStudent.isPresent()) {
            Student currentStudent = optionalStudent.get();
            String fileName= String.format("%d_%s.jpg",currentStudent.id,currentStudent.name);
            File imageFile = new File("./src/main/resources/static/Student_images/"+ fileName);
            if (imageFile.exists()) {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.IMAGE_JPEG);
                return new ResponseEntity<>(Files.readAllBytes(imageFile.toPath()), httpHeaders, HttpStatus.OK);
            }
        }
        // Return an error response if student is not found or image file not found
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
