package com.example.StudentLibraryManagement.Repository;

import com.example.StudentLibraryManagement.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<LibraryCard,Integer>{
}
