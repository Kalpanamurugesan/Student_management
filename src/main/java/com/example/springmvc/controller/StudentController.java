package com.example.springmvc.controller;

import com.example.springmvc.model.Student;
import com.example.springmvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StudentController {
  @Autowired
  private StudentService studentService;
   @GetMapping("/all")
   public ResponseEntity<List<Student>> getStudents(){
       List<Student> studentList = studentService.getAllStudents();
       if(!studentList.isEmpty()){
           return new ResponseEntity<>(studentList,HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
  }
   @GetMapping("/{id}")
   public ResponseEntity<Student> getStudent(@PathVariable String id) {
       Student student = studentService.getStudentById(Integer.parseInt(id));
       if (student != null) {
           return new ResponseEntity<>(student, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
   @GetMapping("/dept/{deptname}")
   public ResponseEntity<List<Student>> getStudentByDept(@PathVariable String deptname){
       List<Student> studentList = studentService.findStudentByDept(deptname);
       if(!studentList.isEmpty()){
           return new ResponseEntity<>(studentList,HttpStatus.OK);
       } else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
   @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
       studentService.addNewStudent(student);
       return new ResponseEntity<>(student,HttpStatus.CREATED);
   }
   @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
       studentService.updateStudentById(student);
       return new ResponseEntity<>(student,HttpStatus.OK);
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudentById(@PathVariable String id){
       String value = studentService.deleteStudentById(Integer.parseInt(id));
       if(value.equals("Successfully deleted"))
           return new ResponseEntity<>(HttpStatus.OK);
       else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
}
