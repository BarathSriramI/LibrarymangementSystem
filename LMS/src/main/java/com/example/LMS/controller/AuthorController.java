package com.example.LMS.controller;


import com.example.LMS.model.Author;
import com.example.LMS.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    // add a author

    @PostMapping("/add")
    public ResponseEntity addauthor( @RequestBody Author author)
    {
        String responsestring= authorService.addauthor(author);

        return new ResponseEntity<>(responsestring, HttpStatus.CREATED);
    }
}
