package com.example.LMS.controller;

import com.example.LMS.Enum.CardStatus;
import com.example.LMS.model.LibraryCard;
import com.example.LMS.model.Student;
import com.example.LMS.service.Studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    Studentservice studentservice;

    // add a student
    @PostMapping("/add")
    public ResponseEntity addstudent ( @RequestBody Student student)
    {

      String responsestudent= studentservice.addstudent(student);
      return new ResponseEntity<>(responsestudent,HttpStatus.CREATED);

    }

    // get student
    @GetMapping("/get")
    public ResponseEntity getstudent (@RequestParam("id") int regNo)
    {
        Student responsestudent = studentservice.getstudent(regNo);

        if(responsestudent!=null) return new ResponseEntity<>(responsestudent,HttpStatus.FOUND);

        return  new ResponseEntity<>("Invalid Regno",HttpStatus.NOT_FOUND);
    }

    //delete a student
    @DeleteMapping("/delete")

    public ResponseEntity deletestudent(@RequestParam("id") int regNo)
    {
        Boolean deleteresponse = studentservice.deletestudent(regNo);

        if(deleteresponse) return new ResponseEntity<>("Student deleted ",HttpStatus.ACCEPTED);

        return new ResponseEntity<>("Inavlid RegNo",HttpStatus.BAD_REQUEST);
    }

    //update the age of student by getting regNo
    @PutMapping("/updateagebyregNo")
    public ResponseEntity updatestudentage(@RequestParam("id") int regNo,@RequestParam("age") int newage)
    {
        Student responsestudent= studentservice.updatestudentage(regNo,newage);

        if(responsestudent!=null) return new ResponseEntity<>(responsestudent,HttpStatus.CREATED);

        return new ResponseEntity<>("Invalid RegNo",HttpStatus.BAD_REQUEST);
    }

    //allstudent
    @GetMapping("/allstudent")

    public List allstudent()
    {
        return studentservice.allstudent();
    }

    //all male student
    @GetMapping("/allmale")
    public List allmale()
    {
        return  studentservice.allmale();
    }
}
