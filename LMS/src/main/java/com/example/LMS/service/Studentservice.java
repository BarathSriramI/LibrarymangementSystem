package com.example.LMS.service;

import com.example.LMS.DTO.requestDTO.StudentRequest;
import com.example.LMS.DTO.responseDTO.Getstudentresponse;
import com.example.LMS.DTO.responseDTO.Librarycardresponse;
import com.example.LMS.DTO.responseDTO.Response;
import com.example.LMS.DTO.responseDTO.StudentResponse;
import com.example.LMS.Enum.CardStatus;
import com.example.LMS.Enum.Gender;
import com.example.LMS.Exception.studentNotFoundExecption;
import com.example.LMS.model.LibraryCard;
import com.example.LMS.model.Student;
import com.example.LMS.repository.StudentRepository;
import com.example.LMS.transformer.LibraryCardTransformer;
import com.example.LMS.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class Studentservice {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    JavaMailSender javaMailSender;

public StudentResponse addstudent(StudentRequest studentRequest) {

//        create a student using builders and storing them in transformers

    Student student = StudentTransformer.studentrequesttoStudent(studentRequest);

    LibraryCard libraryCard = LibraryCardTransformer.inputcardforstudent(student);

    student.setLibraryCard(libraryCard);

    Student studentsaved = studentRepository.save(student);

    StudentResponse studentResponse = StudentTransformer.studenttostudentresponse(studentsaved);

    Librarycardresponse librarycardresponse = LibraryCardTransformer.createcardfromsavedstudent(studentsaved);

     studentResponse.setLibrarycardresponse(librarycardresponse);
     // need to send mail  when person is added

    String text = "Congrats!  Account has been created for " + studentsaved.getName() +"\n" +
            "The student RegNo is" +studentsaved.getRegNo();

    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom("springbackendtest@gmail.com");
    simpleMailMessage.setTo(studentsaved.getEmailid());
    simpleMailMessage.setSubject("Account creation");
    simpleMailMessage.setText(text);

    javaMailSender.send(simpleMailMessage);

    return studentResponse;
    }

    // what should we return
    //name,regno,emailid,age,gender,librarycardNo,transactionlist
    public Getstudentresponse getstudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);

        if (!studentOptional.isEmpty()) {
           Getstudentresponse studentResponse= StudentTransformer.getstudenttostduentresponse(studentOptional.get());

           Librarycardresponse librarycardresponse =  LibraryCardTransformer.createcardfromsavedstudent(studentOptional.get());

           return studentResponse;

        }
        return null;
    }

    public Response deletestudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);


        if (studentOptional.isEmpty()){
             return Response.builder()
                    .found(false)
                    .message("Student with the RegNo  " +regNo +" not found")
                    .build();
        }
             studentRepository.delete(studentOptional.get());
           return  Response.builder()
                    .found(true)
                    .message("Student with the RegNo  " +regNo +" has been deleted")
                   .build();
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