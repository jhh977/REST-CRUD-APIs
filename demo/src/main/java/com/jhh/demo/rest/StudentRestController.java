package com.jhh.demo.rest;

import com.jhh.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudent;

//    define @PostConstructor to load the student data only once
    @PostConstruct
    public void loadData(){
        theStudent = new ArrayList<Student>();
        theStudent.add( new Student("John","Doe"));
        theStudent.add( new Student("Muhammad","Ali"));
        theStudent.add( new Student("Jason","Voohres"));
    }

//    define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudent;
    }

//    define endpoint at "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId)
    {
        return theStudent.get(studentId);
    }
}
