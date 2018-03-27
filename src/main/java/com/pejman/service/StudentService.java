package com.pejman.service;

import java.util.List;

import com.pejman.dao.IStudentDAO;
import com.pejman.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {
	@Autowired
	private IStudentDAO studentDAO;


	@Override
	public Student getStudentByName(String sname) {
        Student obj = studentDAO.getStudentByName(sname);
		return obj;
	}

	@Override
	public List<Student> getAllStudents(){
		return studentDAO.getAllStudents();
	}


	@Override
	public synchronized boolean addStudent(Student student){
       if (studentDAO.studentExists(student.getSname(), student.getSdeg())) {
    	   return false;
       } else {
    	   studentDAO.addStudent(student);
    	   return true;
       }
	}
	@Override
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}

	@Override
	public void deleteStudent(String sname) {
		studentDAO.deleteStudent(sname);
	}
}
