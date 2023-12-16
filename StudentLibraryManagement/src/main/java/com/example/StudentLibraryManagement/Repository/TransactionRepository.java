package com.example.StudentLibraryManagement.Repository;

import com.example.StudentLibraryManagement.Entities.Book;
import com.example.StudentLibraryManagement.Entities.LibraryCard;
import com.example.StudentLibraryManagement.Entities.Transaction;
import com.example.StudentLibraryManagement.Enums.CardStatus;
import com.example.StudentLibraryManagement.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findTransactionByBookAndLibrarycardAndTransactionStatus(Book book, LibraryCard libraryCard, TransactionStatus transactionStatus);
}
