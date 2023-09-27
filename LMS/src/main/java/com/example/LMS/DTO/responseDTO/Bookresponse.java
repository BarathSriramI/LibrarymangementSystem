package com.example.LMS.DTO.responseDTO;

import com.example.LMS.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Bookresponse {

    String bookname;
    double cost;
    Genre genre;
    String authorName;

}
