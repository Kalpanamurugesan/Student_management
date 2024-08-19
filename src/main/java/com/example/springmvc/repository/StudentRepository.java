package com.example.springmvc.repository;

import com.example.springmvc.model.Student;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student,Integer>{
  List<Student> findByStudentDept(String dept);

}
