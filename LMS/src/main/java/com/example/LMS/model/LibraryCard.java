package com.example.LMS.model;

import com.example.LMS.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //unique library card created by UUID

    String librarycardNo;

    @CreationTimestamp
    Date issudate;

    @Enumerated(EnumType.STRING)
    CardStatus status;

    // mapping the library card with student
    @OneToOne
    // creating a forign key in the child table with primarykey of the parent
    @JoinColumn
    Student student;

        @OneToMany(mappedBy = "librarycard",cascade = CascadeType.ALL)
        List<Transaction> transactionList = new ArrayList<>();


}
