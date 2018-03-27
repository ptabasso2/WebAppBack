package com.pejman.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet row, int rowNum) throws SQLException {
		Student student = new Student();
		student.setSemail(row.getString("semail"));
		student.setSpass(row.getString("spass"));
		student.setSname(row.getString("sname"));
		student.setSdeg(row.getString("sdeg"));
		return student;
	}

}
