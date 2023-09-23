package com.example.LMS.DTO.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StudentResponse {

    int RegNo;

    String name;

    String message;

    String librarycardNo;


}
