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
    int regNo;
    String name;
    int age;
    String emailid;
    @Enumerated(EnumType.STRING )
    Gender gender;


}
