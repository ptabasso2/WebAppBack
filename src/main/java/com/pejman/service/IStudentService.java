package com.pejman.service;

import java.util.List;

import com.pejman.entity.Student;

public interface IStudentService {
     List<Student> getAllStudents();
     Student getStudentByName(String sname);
     boolean addStudent(Student student);
     void updateStudent(Student student);
     void deleteStudent(String sname);
}
