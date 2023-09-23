package com.example.LMS.service;

import com.example.LMS.DTO.requestDTO.StudentRequest;
import com.example.LMS.DTO.responseDTO.StudentResponse;
import com.example.LMS.model.Author;
import com.example.LMS.model.Book;
import com.example.LMS.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String addauthor(Author author) {

        Author savedauthor = authorRepository.save(author);
        return "Author created successfully";
    }

    public String updateemailid(int id,String emailid) {

        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if (optionalAuthor.isEmpty()) throw new RuntimeException("Inavlid Author Id !!");

        Author author =optionalAuthor.get();
        author.setEmailid(emailid);
        authorRepository.save(author);

        return "EmailId has been updated";

    }

    public List<String> booksbyauthor(String name) {

        Author author=authorRepository.findByName(name);

        List<Book> bookList= author.getBook();

        List<String> booksname= new ArrayList<>();
        for(Book book : bookList)
        {
            booksname.add(book.getBookName());
        }

            return booksname;
    }

    public List<String> authorname(int booknumber) {

        List<Author> optionalAuthor = authorRepository.findAll();
        List<String> authorname = new ArrayList<>();

        for(Author author : optionalAuthor)
        {
            List<Book> bookList = author.getBook();
            if(bookList.size()>=booknumber)
            {
                authorname.add(author.getName());
            }
        }
        return authorname;

    }
}
