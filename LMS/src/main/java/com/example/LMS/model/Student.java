package com.example.LMS.model;

import com.example.LMS.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int regNo;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int age;
    @Column(unique = true,nullable = false)
    String emailid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING )
    Gender gender;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    LibraryCard libraryCard;


}
