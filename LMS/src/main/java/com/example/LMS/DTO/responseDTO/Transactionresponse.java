package com.example.LMS.DTO.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Transactionresponse {

    String transactionnumber;
    int studentId;
    Date transactiondate;
    String bookname;
    String authorname;

}
