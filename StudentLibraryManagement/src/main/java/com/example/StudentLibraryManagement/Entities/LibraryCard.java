package com.example.StudentLibraryManagement.Entities;

import com.example.StudentLibraryManagement.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CardId;
    @Enumerated(value= EnumType.STRING)
    private CardStatus cardStatus;
    private String NameOnCard;
    private Integer CountOfBooksIssued;

    @OneToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy = "librarycard",cascade = CascadeType.ALL)
    private List<Transaction> ListOfTransactions=new ArrayList<>();
}
