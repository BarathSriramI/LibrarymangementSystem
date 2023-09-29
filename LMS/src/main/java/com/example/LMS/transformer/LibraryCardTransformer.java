package com.example.LMS.transformer;

import com.example.LMS.DTO.responseDTO.Librarycardresponse;
import com.example.LMS.Enum.CardStatus;
import com.example.LMS.model.LibraryCard;
import com.example.LMS.model.Student;

import java.util.UUID;

public class LibraryCardTransformer {

    public static Librarycardresponse createcardfromsavedstudent(Student studentsaved)
    {
        Librarycardresponse librarycardresponse = Librarycardresponse.builder()
                .issudate(studentsaved.getLibraryCard().getIssudate())
                .librarycardNo(studentsaved.getLibraryCard().getLibrarycardNo())
                .status(studentsaved.getLibraryCard().getStatus())
                .build();
        return librarycardresponse;
    }

    public static LibraryCard inputcardforstudent(Student student)
    {
        LibraryCard libraryCard = LibraryCard.builder()
                .librarycardNo(String.valueOf(UUID.randomUUID()))
                .status(CardStatus.Active)
                .student(student)
                .build();
        return libraryCard;
    }
}
