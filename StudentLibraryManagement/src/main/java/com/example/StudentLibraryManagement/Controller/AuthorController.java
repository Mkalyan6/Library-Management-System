package com.example.StudentLibraryManagement.Controller;

import com.example.StudentLibraryManagement.Entities.Author;
import com.example.StudentLibraryManagement.Entities.Book;
import com.example.StudentLibraryManagement.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Author")
        public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/addAuthor")
    public ResponseEntity<String> AddAuthor(@RequestBody Author author) {
     return ResponseEntity.ok(authorService.AddAuthor(author));
    }

    @GetMapping("/AllAuthorsList")
    public List<String> getAllAuthors(){
        return authorService.getAllAuthors();
    }


    @GetMapping("/GetAuthorById")
    public ResponseEntity GetAuthorById(@RequestParam("q")int id){
        try{
            Author a= authorService.GetAuthorById(id);
            return new ResponseEntity(a,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/BookListForAuthor")
    public ResponseEntity BookListForAuthor(@RequestParam("authorId")Integer authorId){
        List<String>bookNames=authorService.BookListForAuthor(authorId);
        return new ResponseEntity(bookNames,HttpStatus.OK);
    }

    }
