package com.example.StudentLibraryManagement.Controller;

import com.example.StudentLibraryManagement.Entities.Student;
import com.example.StudentLibraryManagement.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
   private StudentService studentService;
    @PostMapping("/AddStudent")
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("GetStudent")
    public ResponseEntity getStudent(@RequestParam("studentId")int studentId){
        return studentService.getStudent(studentId);
    }
}
