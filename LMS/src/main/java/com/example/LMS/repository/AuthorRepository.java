package com.example.LMS.repository;

import com.example.LMS.model.Author;
import com.example.LMS.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

     Author findByName(String name);
}
