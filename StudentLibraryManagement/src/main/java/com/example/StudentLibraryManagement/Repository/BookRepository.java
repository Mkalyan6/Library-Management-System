package com.example.StudentLibraryManagement.Repository;

import com.example.StudentLibraryManagement.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
