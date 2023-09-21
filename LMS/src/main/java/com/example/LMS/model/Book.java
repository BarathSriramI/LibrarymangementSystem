package com.example.LMS.model;

import com.example.LMS.Enum.Genre;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    int id;

    String bookName;

    int noofPages;

    double bookCost;

    @Enumerated(EnumType.STRING)
    Genre genre;

    @ManyToOne
    @JoinColumn
    Author author;

}
