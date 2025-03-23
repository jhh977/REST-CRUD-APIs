package com.jhh.demo.rest;

import com.jhh.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//        check the studentID against the list size

        if(studentId > theStudent.size() || studentId < 0)
        {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudent.get(studentId);
    }

//    add an exception handler using @ExceptionHandler

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
//        create studentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
//        return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
