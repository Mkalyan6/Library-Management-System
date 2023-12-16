package com.example.StudentLibraryManagement.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import static jakarta.persistence.GenerationType.*;
import static org.hibernate.annotations.CascadeType.DELETE;

@Entity
@Table(name="Student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
//    @JsonProperty("StudentId")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    @Column(name="StudentName",unique = true,nullable = false)
    private  String name;
    private Integer phone_num;
    private Integer age;
    private String maiId;
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private LibraryCard libraryCard;

}
