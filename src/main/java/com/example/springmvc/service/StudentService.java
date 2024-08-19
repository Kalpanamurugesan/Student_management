package com.example.springmvc.service;

import com.example.springmvc.exception.StudentNotFoundException;
import com.example.springmvc.model.Student;
import com.example.springmvc.repository.StudentRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    //return all the students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findStudentByDept(String dept){ return studentRepository.findByStudentDept(dept);}

    //return specific student by id
    public Student getStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent())
             return student.get();
        else
            throw new StudentNotFoundException("Student not found with the given id for getting");
    }

    //add new student into the database
    public Student addNewStudent(Student student) {
        Optional<Student> student1 = studentRepository.findById(student.getStudentId());
         if(student1.isPresent()){
            throw new DuplicateRequestException("The Student ID already exists in the database");
        }
        else {
            return studentRepository.save(student);
        }
    }

    //update the details of an existing student by id
    public Student updateStudentById(Student student) {
        Optional<Student> student1 = studentRepository.findById(student.getStudentId());
        if(student1.isPresent()){
            return studentRepository.save(student1.get());
        }else{
            throw new StudentNotFoundException("With that given Id student is Not for updating");
        }
    }

    //delete the particular student record by id
    public String deleteStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()){
            studentRepository.deleteById(id);
            return "Successfully deleted";
        }
        else
            throw new StudentNotFoundException("With that given id student is not present for delete");
    }
}
