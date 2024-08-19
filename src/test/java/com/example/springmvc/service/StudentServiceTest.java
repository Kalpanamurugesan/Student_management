package com.example.springmvc.service;

import com.example.springmvc.model.Student;
import com.example.springmvc.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
@Mock
private StudentRepository studentRepository;
@InjectMocks
private StudentService studentService;
@BeforeEach
public void setup(){
    MockitoAnnotations.openMocks(this);
}
@Test
public void getAllStudents() {
    Student s1 = new Student(14, "Gopal", "ECE");
    Student s2 = new Student(11, "Tamil", "CA");
    List<Student> studentlist = new ArrayList<>();
    studentlist.add(s1);
    studentlist.add(s2);
    when(studentRepository.findAll()).thenReturn(studentlist);
    List<Student> students = studentService.getAllStudents();
    Assertions.assertEquals(students.size(), 2);
    Assertions.assertEquals(students.get(0).getStudentName(),"Gopal");
    Assertions.assertEquals(students.get(1).getStudentDept(),"CA");
}
@Test
public void getStudentById() {
   Student s = new Student(13,"Bala","IT");
   when(studentRepository.findById(13)).thenReturn(Optional.of(s));
   Student student = studentService.getStudentById(13);
   org.assertj.core.api.Assertions.assertThat(student.getStudentId()).isEqualTo(13);
   Assertions.assertEquals(s.getStudentDept(),"IT");
}
@Test
public void studentNotFound(){

}
@Test
public void addNewStudent(){
    Student s = new Student(11,"kiran","MECH");
    when(studentRepository.save(s)).thenReturn(s);
    Student savedStudent = studentService.addNewStudent(s);
    Assertions.assertEquals("MECH",savedStudent.getStudentDept());
    Assertions.assertEquals("kiran",savedStudent.getStudentName());
    org.assertj.core.api.Assertions.assertThat(s).isNotNull();
    org.assertj.core.api.Assertions.assertThat(savedStudent.getStudentId()).isGreaterThan(0);
}
@Test
public void findStudentsByDept(){
    Student student = new Student(1,"Tamil","CSE");
    Student student1 = new Student(2,"Renoline","CSE");
    List<Student> studentList = new ArrayList<>();
    studentList.add(student);
    studentList.add(student1);
    when(studentRepository.findByStudentDept("CSE")).thenReturn(studentList);
    List<Student> studentList1 = studentService.findStudentByDept("CSE");
    org.assertj.core.api.Assertions.assertThat(studentList1).isNotNull();
    Assertions.assertEquals(studentList1.size(),2);
    Assertions.assertEquals(studentList1.get(0).getStudentName(),"Tamil");
    Assertions.assertEquals(studentList1.get(1).getStudentName(),"Renoline");
}
//@Test
//public void updateStudentById(){
// Student student = new Student(19,"Mahir","CSE");
// when(studentRepository.save(student)).thenReturn(student);
// student.setStudentName("Sheik");
// student.setStudentDept("IT");
// Student updatedStudent = studentService.updateStudentById(student);
// org.assertj.core.api.Assertions.assertThat(updatedStudent).isNotNull();
// Assertions.assertEquals(updatedStudent.getStudentName(),"Sheik");
// Assertions.assertEquals(updatedStudent.getStudentDept(),"IT");
//}
}