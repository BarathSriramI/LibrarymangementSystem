package com.example.LMS.controller;


import com.example.LMS.model.Author;
import com.example.LMS.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // update the emailid of the author
    @PutMapping("/updateemail")
    public String updateemailid( @RequestParam("id") int id, @RequestParam("mail") String emailid)
    {
        try {
             return authorService.updateemailid(id, emailid);
           }
        catch(Exception e)
        {
            return e.getMessage();
        }
    }


    // give the name of all the books written by a paricular author
    @GetMapping("/booksbyauthor")
    public ResponseEntity booksbyauthor(@RequestParam("name")String name)
    {
        List<String> bookname = authorService.booksbyauthor(name);
        if(bookname.isEmpty()) return new ResponseEntity<>("Author  has not written any Book",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(bookname,HttpStatus.FOUND);
    }

    // give the name of the author who has written x no of books
    @GetMapping("/authorlist")
    public ResponseEntity authorname(@RequestParam("id") int booknumber)
    {
        List <String > authorname = authorService.authorname(booknumber);

        if(authorname.isEmpty()) return new ResponseEntity<>("No author found",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(authorname,HttpStatus.FOUND);
    }


}
