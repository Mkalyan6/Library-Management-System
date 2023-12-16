package com.example.StudentLibraryManagement.Controller;

import com.example.StudentLibraryManagement.Entities.LibraryCard;
import com.example.StudentLibraryManagement.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Card")


public class CardController {
    @Autowired
    private CardService cardService;
    @PostMapping("/GenerateCard")
 public ResponseEntity addNewCard(){

     LibraryCard card= cardService.addNewCard();
     String message="The new library card has been added successfully with Card Number="+card.getCardId();
     return new ResponseEntity(message, HttpStatus.OK);
 }

 @PutMapping ("/AssociateCardAndStudent")
    public ResponseEntity associateCardAndStudent(@RequestParam("carId")Integer cardId,@RequestParam("StudentId")Integer Stid){
        cardService.associateCardAndStudent(cardId,Stid);
        return new ResponseEntity("Studen and Library card has been linked",HttpStatus.OK);
 }
}
