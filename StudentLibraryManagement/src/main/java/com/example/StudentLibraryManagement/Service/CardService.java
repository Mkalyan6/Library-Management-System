package com.example.StudentLibraryManagement.Service;

import com.example.StudentLibraryManagement.Entities.LibraryCard;
import com.example.StudentLibraryManagement.Entities.Student;
import com.example.StudentLibraryManagement.Enums.CardStatus;
import com.example.StudentLibraryManagement.Repository.CardRepository;
import com.example.StudentLibraryManagement.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    StudentRepository studentRepository;
    public LibraryCard addNewCard() {
        // Whenever this add new card is calle, it will generate a new card with id number and with card status,
        // It means that it is plian card with status generated,
        // When ever student is attached with the saved card, than it will be alloted to particular student;
        LibraryCard newCard= new LibraryCard();
        newCard.setCardStatus(CardStatus.NEW);
        // Now save this card to table,
        cardRepository.save(newCard);
        return newCard;
    }

    public void associateCardAndStudent(Integer cardId, Integer stid) {
        // Now we have to get the object of library card and student from the database usng these primarykeys
        Optional<Student> optionalStudent=studentRepository.findById(stid);
        Student student=optionalStudent.get();
        Optional<LibraryCard>optionalLibraryCard=cardRepository.findById(cardId);
        LibraryCard libraryCard=optionalLibraryCard.get();
        // we assumed that both the cards were present with the given id

        // Now we have to set the values that needs to be change before adding to database;
         student.setLibraryCard(libraryCard);
         libraryCard.setNameOnCard(student.getName());
         libraryCard.setCardStatus(CardStatus.ACTIVE);
         libraryCard.setStudent(student);


         // Now we change the attrubutes values of objects, and now have to add to db the updated objects
        // Due to Bidirectional Mapping  If the parent object is saved to db, then due to cascading to lower levels annotation,
        // It gets automacially saves the child object in db
        studentRepository.save(student);

    }
}
