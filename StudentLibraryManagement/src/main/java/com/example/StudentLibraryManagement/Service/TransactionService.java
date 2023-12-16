package com.example.StudentLibraryManagement.Service;

import com.example.StudentLibraryManagement.Entities.Book;
import com.example.StudentLibraryManagement.Entities.LibraryCard;
import com.example.StudentLibraryManagement.Entities.Transaction;
import com.example.StudentLibraryManagement.Enums.CardStatus;
import com.example.StudentLibraryManagement.Enums.TransactionStatus;
import com.example.StudentLibraryManagement.Exceptions.*;
import com.example.StudentLibraryManagement.Repository.BookRepository;
import com.example.StudentLibraryManagement.Repository.CardRepository;
import com.example.StudentLibraryManagement.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    private static int MAX_LIMITOfBooks=3;
    private static int FINE_PER_DAY=5;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;

    public String issueBook(Integer bookId, Integer cardId) throws Exception {
        Transaction transaction=new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        // first get the book object and card object from the tables
        Optional<Book> bookOptional= bookRepository.findById(bookId);
        if(!bookOptional.isPresent()){
            throw new BookNotFound("BooKId is invalid,So cannot Issue this to student");
        }
        Optional<LibraryCard>optionalLibraryCard=cardRepository.findById(cardId);
        if(!optionalLibraryCard.isPresent()){
            throw new CardNotFound("LibraryCardId is invalid, So cannot Issue book to this invalid Card");
        }


        Book book=bookOptional.get();
        LibraryCard libraryCard=optionalLibraryCard.get();

        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new CardStatusInvalidException("Card Is Not Valid For issuing of the Book");
        }
        if(libraryCard.getCountOfBooksIssued()==MAX_LIMITOfBooks){
           throw new CardLimitReached("Already reached the limit of"+MAX_LIMITOfBooks+"Books per Card");
        }
        if(!book.isIsBookAvailable()){
            throw new BookNotAvailableException("This book is unavailable at present");
        }

        transaction.setBook(book);
        transaction.setLibrarycard(libraryCard);
        transaction.setTransactionStatus(TransactionStatus.ISSUED);

        // Increase the count of books issued on library card;
        libraryCard.setCountOfBooksIssued(libraryCard.getCountOfBooksIssued()+1);
        // Set the book is not available for issuing for other person, as this is issued to this
        // Particular card
        book.setIsBookAvailable(false);

        // Add the foreign keys to columns
        transaction.setBook(book);
        transaction.setLibrarycard(libraryCard);

//        Note this Transactioin in the book and library card attributes;
        book.getListOfTransactions().add(transaction);
        libraryCard.getListOfTransactions().add(transaction);

        // Now add the parent classes to te db. then transaction is the child class,
        // which automatically gets added to db
        // If we save both the parents , the commom child will get saved or updates 2 times dud to
        // cascading effect,
        // so cascading works in two ways, means, if child gets saved automatically parent gets saved;
//        bookRepository.save(book);
//        cardRepository.save(card);
          transactionRepository.save(transaction);
        return "The Book has been successfully issued to "+libraryCard.getCardId()+" and Noted the transaction";
    }

    public String ReturnBook(Integer bookId, Integer cardId) {
        // first we have to check the particulr book is being issued to this card, and still the staus should be issue
        // Because previoulsly also the book might issued to same card and being returned .. that transation is complete
        // cond : bookId+cardId+ TransactionStatus: Issue
        Book book=bookRepository.findById(bookId).get();
        LibraryCard libraryCard=cardRepository.findById(cardId).get();

        Transaction transaction=transactionRepository.findTransactionByBookAndLibrarycardAndTransactionStatus(book,libraryCard,TransactionStatus.ISSUED);
        // through this method calling we get the transaction with the book, and card and still issue transaction is there


        // From this transaction we get the issue date
        Date issueDate=transaction.getCreatedOn();

        // We got the issue date, calculate no of days till now, and tell the fine;
        long milliSeconds=Math.abs(System.currentTimeMillis()-issueDate.getTime());
        long days= TimeUnit.DAYS.convert(milliSeconds,TimeUnit.MILLISECONDS);

        int totalFine=0;
        // Calculate the fine amount;
        if(days>15)
         totalFine= Math.toIntExact(days-15)*FINE_PER_DAY;

        // Make a return transacation
        Transaction ReturnTransaction=new Transaction();
        ReturnTransaction.setIssueDate(issueDate);
        ReturnTransaction.setReturnDate(new Date());
        ReturnTransaction.setTransactionStatus(TransactionStatus.RETURNED);
        ReturnTransaction.setBook(book);
        ReturnTransaction.setLibrarycard(libraryCard);

        // edit the parent entities
        book.setIsBookAvailable(true);
        ReturnTransaction.setFine(totalFine);
        libraryCard.setCountOfBooksIssued(libraryCard.getCountOfBooksIssued()-1);

        book.getListOfTransactions().add(ReturnTransaction);
        libraryCard.getListOfTransactions().add(ReturnTransaction);

        transactionRepository.save(ReturnTransaction);

         return "The book has been returned ";


    }
}
