package com.example.LMS.transformer;

import com.example.LMS.DTO.requestDTO.StudentRequest;
import com.example.LMS.DTO.responseDTO.Getstudentresponse;
import com.example.LMS.DTO.responseDTO.StudentResponse;
import com.example.LMS.model.Student;

public class StudentTransformer {

    // Here we will have studentrequest to student tranformers and student to student response tranfomers

    public  static Student studentrequesttoStudent (StudentRequest studentRequest)
    {
        return  Student.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .emailid(studentRequest.getEmailid())
                .gender(studentRequest.getGender())
                .build();
    }
    public  static StudentResponse studenttostudentresponse(Student studentsaved)
    {
        StudentResponse studentResponse = StudentResponse.builder()
                .RegNo(studentsaved.getRegNo())
                .name(studentsaved.getName())
                .message("Student has been Successfully created")
                .build();
        return studentResponse;
    }
    public static Getstudentresponse getstudenttostduentresponse(Student student)
    {

        Getstudentresponse getstudentresponse = Getstudentresponse.builder()
                .name(student.getName())
                .regNo(student.getRegNo())
                .age(student.getAge())
                .emailid(student.getEmailid())
                .gender(student.getGender())
                .libraryCardno(student.getLibraryCard().getLibrarycardNo())
                .build();

        return getstudentresponse;
    }
}
