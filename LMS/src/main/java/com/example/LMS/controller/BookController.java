package com.example.LMS.controller;

import com.example.LMS.model.Book;
import com.example.LMS.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    // add a book
    @PostMapping("/add")
    public String addbook( @RequestBody Book book)
    {
        return bookService.addbook(book);
    }

}
