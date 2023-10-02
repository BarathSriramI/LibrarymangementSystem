package com.example.LMS.DTO.responseDTO;

import com.example.LMS.Enum.Gender;
import com.example.LMS.model.Book;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Getstudentresponse {

    // what should we return
    //name,regno,emailid,age,gender,librarycardNo,transactionlist

     String name;
     int regNo;
     String emailid;
     int age;
     Gender gender;
     String libraryCardno;
}
