package com.example.StudentLibraryManagement.Service;

import com.example.StudentLibraryManagement.Entities.Author;
import com.example.StudentLibraryManagement.Entities.Book;
import com.example.StudentLibraryManagement.Exceptions.AuthorNotFound;
import com.example.StudentLibraryManagement.Repository.AuthorRepository;
import com.example.StudentLibraryManagement.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    public String AddBook(Book book, Integer authorId) throws Exception{
        // Throw this authorid we have to find the author object
        // modifty the objects from book , and author, by forming a connection between both
        Optional<Author> optionalAuthor=authorRepository.findById(authorId);

        // If no author is found with that id, we have to thow exception
        if(!optionalAuthor.isPresent()){
            // throw customException
            throw new AuthorNotFound("Invalid AuthorId ,Cannot Add a book with this Invalid Author");

        }
        Author author=optionalAuthor.get();
        // Now we have to assing this author to book object attribute;
        book.setAuthor(author);
        author.getBookList().add(book);
        // Now save the parent entity, then child entitty automatically gets saved due to cascading
        authorRepository.save(author);
        return "The Book has been succesfully added to database with author name attaching";

    }

}
