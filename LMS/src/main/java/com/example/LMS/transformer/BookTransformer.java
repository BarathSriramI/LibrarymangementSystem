package com.example.LMS.transformer;

import com.example.LMS.DTO.responseDTO.Bookresponse;
import com.example.LMS.model.Book;

public class BookTransformer {

    public static Bookresponse booktobookresponse(Book book)
    {
        Bookresponse bookresponse = Bookresponse.builder()
                .bookname(book.getBookName())
                .cost((book.getBookCost()))
                .genre(book.getGenre())
                .authorName(book.getAuthor().getName())
                .build();
        return bookresponse;
    }
}
