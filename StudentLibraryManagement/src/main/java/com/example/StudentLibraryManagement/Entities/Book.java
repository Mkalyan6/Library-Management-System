package com.example.StudentLibraryManagement.Entities;

import com.example.StudentLibraryManagement.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="BookDetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String bookName;
    private int noOfPages;
    @Enumerated(value=EnumType.STRING)
    private Genre genre;
    private double rating;
    boolean IsBookAvailable;

    @ManyToOne
    @JoinColumn
    private Author author;
    // Author is pending for the particular book;

    // BidirectionalMapping b/w book and transaction, and book is parent table
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
     private List<Transaction> ListOfTransactions=new ArrayList<>();
}
