package com.example.LMS.service;

import com.example.LMS.DTO.requestDTO.StudentRequest;
import com.example.LMS.DTO.responseDTO.Librarycardresponse;
import com.example.LMS.DTO.responseDTO.StudentResponse;
import com.example.LMS.Enum.CardStatus;
import com.example.LMS.Enum.Gender;
import com.example.LMS.model.LibraryCard;
import com.example.LMS.model.Student;
import com.example.LMS.repository.StudentRepository;
import com.example.LMS.transformer.LibraryCardTransformer;
import com.example.LMS.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class Studentservice {

    @Autowired
    StudentRepository studentRepository;

public StudentResponse addstudent(StudentRequest studentRequest) {

//        create a student using builders and storing them in transformers

    Student student = StudentTransformer.studentrequesttoStudent(studentRequest);

    LibraryCard libraryCard = LibraryCardTransformer.inputcardforstudent(student);

    student.setLibraryCard(libraryCard);

    Student studentsaved = studentRepository.save(student);

    StudentResponse studentResponse = StudentTransformer.studenttostudentresponse(studentsaved);

    Librarycardresponse librarycardresponse = LibraryCardTransformer.createcardfromsavedstudent(studentsaved);

     studentResponse.setLibrarycardresponse(librarycardresponse);

    return studentResponse;
    }

    public Student getstudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);

        if (!studentOptional.isEmpty()) {
            return studentOptional.get();
        }
        return null;
    }

    public boolean deletestudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);

        if (studentOptional.isEmpty())
            return false;

        studentRepository.delete(studentOptional.get());
        return true;
    }

    public Student updatestudentage(int regNo, int newage) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);

        if (studentOptional.isEmpty())
            return null;

        Student updatestudent = studentOptional.get();
        updatestudent.setAge(newage);
        return studentRepository.save(updatestudent);

    }

    public List allstudent() {
        List<Student> allstudent = studentRepository.findAll();
        return allstudent;
    }

    public List allmale() {
        List<Student> allstudent = studentRepository.findAll();

        String checkgender = "male";

        List<Student> allmale = new ArrayList<>();

        for (Student student : allstudent) {
            String gender = String.valueOf(student.getGender());
            if ((gender.equalsIgnoreCase(checkgender))) {
                allmale.add(student);
            }

        }
        return allmale;
    }

    public List<String> malenames() {

        List<Student> studentlist= studentRepository.findByGender(Gender.MALE);

        List<String> malenames=new ArrayList<>();

        for(Student s : studentlist) malenames.add(s.getName());
        return malenames;
    }
}