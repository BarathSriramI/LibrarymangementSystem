package com.example.LMS.controller;

import com.example.LMS.DTO.responseDTO.Transactionresponse;
import com.example.LMS.service.TransactionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transaction")
public class Transactioncontroller {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue/book-id/{book-id}/student-id/{student-id}")
    public ResponseEntity issueBook(@PathVariable("book-id") int bookId,
                                    @PathVariable("student-id") int studentId){

        try{
            Transactionresponse response = transactionService.issueBook(bookId,studentId);
            return new ResponseEntity(response,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // return book API

    @PostMapping("/return/book-id/{book-id}/student-id/{student-id}")
    public ResponseEntity returnbook(@PathVariable("book-id") int bookId,
                                     @PathVariable("student-id") int studentId)
    {
        try{
             Transactionresponse transactionresponse = transactionService.returnbook(bookId,studentId);
             return new ResponseEntity<>(transactionresponse,HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

}
