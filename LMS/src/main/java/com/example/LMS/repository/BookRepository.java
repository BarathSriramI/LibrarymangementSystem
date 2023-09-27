package com.example.LMS.repository;

import com.example.LMS.Enum.Genre;
import com.example.LMS.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book>findByGenre(Genre genre);

    //sql query
    @Query(value = "select * from book where genre = :genre and book_cost >= :cost",nativeQuery = true)
    List<Book> booksbasedongenreAndcostgreater(String genre,double cost);
    // hql query
    @Query(value = "select b from Book b where b.genre = :genre and b.bookCost >= :cost")
   List<Book> booksbasedongenreAndcostgreaterhql(Genre genre,double cost);

}
