package com.example.LMS.DTO.requestDTO;

import com.example.LMS.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    String name;

    int age;

    String emailid;

    Gender gender;

}
