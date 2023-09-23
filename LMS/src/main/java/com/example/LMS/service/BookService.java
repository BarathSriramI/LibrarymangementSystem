package com.example.LMS.service;

import com.example.LMS.Enum.Genre;
import com.example.LMS.Exception.AuthorNotFoundException;
import com.example.LMS.Exception.BookNotFound;
import com.example.LMS.model.Author;
import com.example.LMS.model.Book;
import com.example.LMS.repository.AuthorRepository;
import com.example.LMS.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    public String addbook(Book book) {

        Optional<Author> optionalauthor = authorRepository.findById(book.getAuthor().getId());

        if(optionalauthor.isEmpty())
        {
          throw new AuthorNotFoundException("Inavlid Author Id!!");
        }

        // if thr author is found
        Author author = optionalauthor.get();

        book.setAuthor(author);
        book.setIssued(false);
        author.getBook().add(book);

        // here we save Author --the parent so the book will also be saved
        authorRepository.save(author);
        return "Book has been Added";
    }

    public String deletebook(int id) {

        Optional<Book> optionalbook = bookRepository.findById(id);

        if(optionalbook.isEmpty()) throw new BookNotFound("Book not found");

        Book book = optionalbook.get();
        bookRepository.delete(book);
        return "The Book has been removed ";

    }

    public List<String> booksbyfictiongenre()
    {

        List<Book> bookList = bookRepository.findByGenre(Genre.FICTION);
        List<String> booksname= new ArrayList<>();

        if(bookList.isEmpty()) return new ArrayList<>();

        for(Book book : bookList)
        {
            booksname.add(book.getBookName());
        }

        return booksname;
    }

    public List<String>   booksingenreandcost(Genre genre, int cost) {

        List<Book> bookList = bookRepository.findByGenre(genre);
        List<String> booksname= new ArrayList<>();

        for(Book b : bookList)
        {
            if(b.getBookCost() >= cost)  booksname.add(b.getBookName());
        }

        return booksname;
    }

    public List<String> authornamebygenre(Genre genre) {
        List<Book> bookList = bookRepository.findByGenre(genre);
        List<String> authorname = new ArrayList<>();

        for(Book book : bookList)
        {
            authorname.add(book.getAuthor().getName());
        }
        return authorname;
    }
}
