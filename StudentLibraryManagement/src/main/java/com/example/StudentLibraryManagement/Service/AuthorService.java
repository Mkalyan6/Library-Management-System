package com.example.StudentLibraryManagement.Service;

import com.example.StudentLibraryManagement.Entities.Author;
import com.example.StudentLibraryManagement.Entities.Book;
import com.example.StudentLibraryManagement.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public String AddAuthor(Author author) {
        authorRepository.save(author);
        return "Author has been successfully added to DB";

    }


    public List<String> getAllAuthors() {
        List<Author> authorList=authorRepository.findAll();
        // Now traverse through the author list and make the list of author names;
        List<String>authorNamesList=new ArrayList<>();
        for(Author a:authorList){
            authorNamesList.add(a.getAuthorName());
        }
        return authorNamesList;
    }

    public Author GetAuthorById(int id) throws Exception {
        // First we have to check if the author is present with the id name,
//         if he is nowt present it throws null, and if we searc the returned onject and its functions, it returns null pointer exception
        // optional type can be returned by our searching
        Optional<Author>OptionalAuthor=authorRepository.findById(id);

        if(!OptionalAuthor.isPresent()){
            // Means returned null, so author is not found , and came to this function
            throw new Exception("The Id provided is not valid, and hence no author found that invalid id");
        }
        return OptionalAuthor.get();
    }
    @GetMapping("/GetBookListNamesForAuthor")
    public List<String> BookListForAuthor(@RequestParam("authorId")Integer authorId){
        List<String>BookNamesList=new ArrayList<>();
        // Get the author object from author table, and
        // then get the booklist object from the attributes
        // traeverse through the book object of list and store the names of books in list and return
        Author author=authorRepository.findById(authorId).get();
        List<Book>BookList=author.getBookList();

        // Now traverse throw th booklist and store the naams of books
        for(Book b:BookList){
            BookNamesList.add(b.getBookName());
        }
        return BookNamesList;
    }
}

