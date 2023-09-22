package com.example.LMS.controller;

import com.example.LMS.Enum.Genre;
import com.example.LMS.Exception.AuthorNotFoundException;
import com.example.LMS.model.Book;
import com.example.LMS.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    // add a book

    // if we are going to add a new book in the Db then we should check whether the author is present
    // and then add the book in the author's list of book
    @PostMapping("/add")
    public String addbook(@RequestBody Book book) {
        try {
            String responseString = bookService.addbook(book);
            return responseString;
        } catch (AuthorNotFoundException e) {
            return e.getMessage();
        }
    }

    // delete a book
    @DeleteMapping("/delete")
    public String deletebook(@RequestParam("id") int id) {
        try {
            String response = bookService.deletebook(id);
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    // give the names of all the books in particular genre
    @GetMapping("/fictiongenre")
    public ResponseEntity booksbyfictiongenre()
    {
        List<String> Bookname = bookService.booksbyfictiongenre();

        if(Bookname.isEmpty()) return new ResponseEntity<>("no books found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(Bookname,HttpStatus.FOUND);
    }

    //give names of all the books in a particular genre and cost greater than x
        @GetMapping("/booksingenreandcost")
    public ResponseEntity booksingenreandcost( @RequestParam("genre") Genre genre, @RequestParam("cost") int cost)
    {
         List <String> bookname = bookService.booksingenreandcost(genre,cost);

         if(bookname.isEmpty()) return  new ResponseEntity<>("no books found",HttpStatus.NOT_FOUND);
         return new ResponseEntity<>(bookname,HttpStatus.FOUND);
    }

    //give names of books having pages between a and b


//    give names of all authors whose write in a particular genre
    @GetMapping("/authorbygenre")
    public List<String> authornamebygenre(@RequestParam("genre") Genre genre)
    {
        List<String> bookname = bookService.authornamebygenre(genre);
        return bookname;
    }


}