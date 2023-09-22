package com.example.LMS.repository;

import com.example.LMS.Enum.Genre;
import com.example.LMS.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book>findByGenre(Genre genre);


}
