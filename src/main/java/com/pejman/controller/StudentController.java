package com.pejman.controller;
import java.util.List;

import com.pejman.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.pejman.service.IStudentService;

@Controller
@RequestMapping("user")
public class StudentController {
	@Autowired
	private IStudentService studentService;
	@GetMapping("student/{sname}")
	public ResponseEntity<Student> getStudentByName(@PathVariable("sname") String sname) {
		Student student = studentService.getStudentByName(sname);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	@GetMapping("students")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> list = studentService.getAllStudents();
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}

	@PostMapping("student")
	public ResponseEntity<Student> addStudent(@RequestBody Student student, UriComponentsBuilder builder) {
        boolean flag = studentService.addStudent(student);
        if (flag == false) {
        	return new ResponseEntity<Student>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/student/{sname}").buildAndExpand(student.getSname()).toUri());
        ResponseEntity<Student> re = new ResponseEntity<>(student, headers, HttpStatus.CREATED);
		System.out.println("Pause");
		return re;
	}

	@PutMapping("student")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		studentService.updateStudent(student);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	@DeleteMapping("student/{sname}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("sname") String sname) {
		studentService.deleteStudent(sname);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 