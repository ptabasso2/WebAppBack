package com.pejman.dao;
import java.util.List;
import com.pejman.entity.Student;

public interface IStudentDAO {
    List<Student> getAllStudents();
    Student getStudentByName(String sname);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(String sname);
    boolean studentExists(String sname, String sdeg);
}
 