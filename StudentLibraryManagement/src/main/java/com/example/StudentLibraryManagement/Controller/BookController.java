package com.example.StudentLibraryManagement.Controller;

import com.example.StudentLibraryManagement.Entities.Book;
import com.example.StudentLibraryManagement.Entities.Author;

import com.example.StudentLibraryManagement.Repository.AuthorRepository;
import com.example.StudentLibraryManagement.Repository.BookRepository;
import com.example.StudentLibraryManagement.Service.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController{
    @Autowired
    BookService bookService;
    @PostMapping("/AddBook")
    public ResponseEntity AddBook(@RequestBody Book book,@RequestParam("AuthorId") Integer AuthorId){
        try{
            String b=bookService.AddBook(book,AuthorId);
            return new ResponseEntity(b, HttpStatus.OK);
        } catch (Exception e) {
             return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }



}
