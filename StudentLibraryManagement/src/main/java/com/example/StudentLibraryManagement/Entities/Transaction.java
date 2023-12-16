package com.example.StudentLibraryManagement.Entities;

import com.example.StudentLibraryManagement.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Entity
@Table

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer transactionId;
    @Enumerated(value=EnumType.STRING)
    private TransactionStatus transactionStatus;
    private Integer fine;
    
    private Date issueDate;
    private Date returnDate;
    @CreationTimestamp
    private Date CreatedOn;
    @UpdateTimestamp
    private Date LastModifiedOn;

    // foreign key will be book,
    @ManyToOne
    @JoinColumn
    private Book book;

    // foreign key will be librarayCard;
    @ManyToOne
    @JoinColumn
    private LibraryCard librarycard;
}
//