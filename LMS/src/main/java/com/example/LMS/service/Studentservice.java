package com.example.LMS.service;

import com.example.LMS.model.Student;
import com.example.LMS.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Studentservice {

    @Autowired
    StudentRepository studentRepository;

    public Student addstudent(Student student) {

        Student studentsaved = studentRepository.save(student);
        return studentsaved;
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
}