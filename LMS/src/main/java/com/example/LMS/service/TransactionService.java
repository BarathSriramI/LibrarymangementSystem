package com.example.LMS.service;

import com.example.LMS.DTO.responseDTO.Transactionresponse;
import com.example.LMS.Enum.Transactionstatus;
import com.example.LMS.Exception.studentNotFoundExecption;
import com.example.LMS.model.Book;
import com.example.LMS.model.Student;
import com.example.LMS.model.Transaction;
import com.example.LMS.repository.BookRepository;
import com.example.LMS.repository.StudentRepository;
import com.example.LMS.repository.TransactionRepository;
import com.example.LMS.transformer.TransactionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionRepository transactionRepository;
    public Transactionresponse issueBook(int bookId, int studentId) {

        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if(studentOptional.isEmpty()) throw new studentNotFoundExecption("Invalid Student Id");

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isEmpty()) throw new RuntimeException("Invalid Book Id");

        Book book = optionalBook.get();
        Student student = studentOptional.get();

        if(book.isIssued()) throw new RuntimeException("book not available");

        Transaction savedtransaction = transactionRepository
                .save(TransactionTransformer.createtransaction(book,student));


        book.setIssued(true);
        book.getTransactionList().add(savedtransaction);


        student.getLibraryCard().getTransactionList().add(savedtransaction);

        bookRepository.save(book);
        studentRepository.save(student);

        return TransactionTransformer.transactionToTransactionresponse(savedtransaction,book);


    }

    public Transactionresponse returnbook(int bookId, int studentId) {

        // check bookid is avlid

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isEmpty()) throw new RuntimeException("Invalid Book Id");

        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if(studentOptional.isEmpty()) throw new studentNotFoundExecption("Invalid Student Id");

        Book book = optionalBook.get();
        Student student = studentOptional.get();

        if(!book.isIssued()) throw new RuntimeException("Book is not issued");

        Transaction savedtransaction= null;

        for(Transaction transaction : student.getLibraryCard().getTransactionList())
        {
            if(!transaction.getBook().equals(book)) throw  new RuntimeException("The transaction doesnot match");

             savedtransaction = transactionRepository
                    .save(TransactionTransformer.createtransaction(book,student));
        }
        book.setIssued(false);
        book.getTransactionList().add(savedtransaction);


        student.getLibraryCard().getTransactionList().add(savedtransaction);

        bookRepository.save(book);
        studentRepository.save(student);

        return TransactionTransformer.transactionToTransactionresponse(savedtransaction,book);
    }
}
