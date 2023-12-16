package com.example.StudentLibraryManagement.Repository;

import com.example.StudentLibraryManagement.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
