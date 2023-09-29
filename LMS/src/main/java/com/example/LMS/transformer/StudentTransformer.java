package com.example.LMS.transformer;

import com.example.LMS.DTO.requestDTO.StudentRequest;
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
}
