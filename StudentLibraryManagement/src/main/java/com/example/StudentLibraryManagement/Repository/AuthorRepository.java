package com.example.StudentLibraryManagement.Repository;

import com.example.StudentLibraryManagement.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
