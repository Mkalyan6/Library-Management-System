package com.example.StudentLibraryManagement.Service;

import com.example.StudentLibraryManagement.Entities.Student;
import com.example.StudentLibraryManagement.Repository.StudentRepository;
import com.example.StudentLibraryManagement.ResponseObjects.StudentBasicDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    JavaMailSender javaMailSender;


    public String addStudent(Student student) {

        studentRepository.save(student);

        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        String body= "The student: "+student.getName()+"successfully registered in school";
        simpleMailMessage.setFrom("kalyanmanukondamail@gmail.com");
        simpleMailMessage.setTo(student.getMaiId());
        simpleMailMessage.setSubject("WELCOME TO THE SCHOOL LIBRARY");
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);

        return "Student data has been added to DB";
    }

    public ResponseEntity getStudent(int studentId) {
        Optional<Student>optionalStudent=studentRepository.findById(studentId);
        Student student=optionalStudent.get();
        if(student!=null){
            // we have to show the selective attributes from a student object
            StudentBasicDetails studentBasicDetails=new StudentBasicDetails();
            studentBasicDetails.setName(student.getName());
            studentBasicDetails.setAge(student.getAge());
            studentBasicDetails.setMaiId(student.getMaiId());
            return new ResponseEntity<>(studentBasicDetails, HttpStatus.OK);
        }
        String message="Student Not Found";
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
}
