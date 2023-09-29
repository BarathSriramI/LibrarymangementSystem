package com.example.LMS.DTO.responseDTO;

import com.example.LMS.Enum.CardStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Librarycardresponse {


    String librarycardNo;
    Date issudate;
    CardStatus status;
}
