package com.example.LMS.transformer;

import com.example.LMS.DTO.responseDTO.Transactionresponse;
import com.example.LMS.Enum.Transactionstatus;
import com.example.LMS.model.Book;
import com.example.LMS.model.Student;
import com.example.LMS.model.Transaction;

import java.util.UUID;

public class TransactionTransformer {

    public static Transaction createtransaction(Book book, Student student)
    {
        Transaction transaction = Transaction.builder()
                .transactionnumber(String.valueOf(UUID.randomUUID()))
                .transactionstatus(Transactionstatus.SUCCESS)
                .book(book)
                .librarycard(student.getLibraryCard())
                .build();
        return transaction;
    }

    public static  Transactionresponse transactionToTransactionresponse(Transaction transaction,Book book)
    {
        Transactionresponse transactionresponse = Transactionresponse.builder()
                .transactionnumber(transaction.getTransactionnumber())
                .transactiondate(transaction.getTransactiondate())
                .bookname(book.getBookName())
                .authorname(book.getAuthor().getName())
                .studentId(transaction.getLibrarycard().getStudent().getRegNo())
                .build();
        return transactionresponse;
    }
}
