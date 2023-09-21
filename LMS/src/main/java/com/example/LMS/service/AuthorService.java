package com.example.LMS.service;

import com.example.LMS.model.Author;
import com.example.LMS.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String addauthor(Author author) {

        Author savedauthor = authorRepository.save(author);
        return "Author created successfully";
    }
}
