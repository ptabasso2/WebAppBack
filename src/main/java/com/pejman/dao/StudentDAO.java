package com.pejman.dao;
import java.util.List;
import java.util.Random;

import com.pejman.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pejman.entity.StudentRowMapper;
@Transactional
@Repository
public class StudentDAO implements IStudentDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }


	@Override
	public Student getStudentByName(String sname) {
//		String sql = "SELECT sname, semail, sdeg, spass FROM student WHERE sname = ?";
		String sql = "SELECT sname, semail, sdeg, spass FROM student, pg_sleep(RANDOM()*((0.5-0.2)+0.2)) WHERE sname = ?";

		RowMapper<Student> rowMapper = new BeanPropertyRowMapper<Student>(Student.class);
		Student student = jdbcTemplate.queryForObject(sql, rowMapper, sname);
		return student;
	}


    @Override
	public List<Student> getAllStudents() {
		//String sql = "SELECT studentId, sname, category FROM students";
        int randValue = randInt(2,20);
//		String sql = "SELECT SNAME,SEMAIL,SPASS,SDEG FROM student";
		String sql = "SELECT SNAME,SEMAIL,SPASS,SDEG FROM student, pg_sleep(RANDOM()*((0.5-0.2)+0.2))";
		RowMapper<Student> rowMapper = new StudentRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}


	@Override
	public void addStudent(Student student) {
		//Add student
//		String sql = "INSERT INTO student (SNAME, SEMAIL, SPASS, SDEG) values (?, ?, ?, ?)";
        String sql = "INSERT INTO student SELECT ? AS SNAME, ? AS SEMAIL, ? AS SPASS, ? AS SDEG from (select pg_sleep(RANDOM()*((0.5-0.2)+0.2))) as t";
		jdbcTemplate.update(sql, student.getSname(), student.getSemail(), student.getSpass(),  student.getSdeg());

		//jdbcTemplate.execute(sql);

		//Fetch student id
//		sql = "SELECT sname FROM student WHERE sname = ? and sdeg=?";
		sql = "SELECT sname FROM student, pg_sleep(RANDOM()*((0.5-0.2)+0.2)) WHERE sname = ? and sdeg=?";
		String sname = jdbcTemplate.queryForObject(sql, String.class, student.getSname(), student.getSdeg());

		//Set student id
		student.setSname(sname);
	}

	@Override
	public void updateStudent(Student student) {
//		String sql = "UPDATE student SET semail=?, spass=?, sdeg=? WHERE sname=?";
		String sql = "UPDATE STUDENT SET SEMAIL=(select t.semail from (select ? as semail) as t), SPASS=(select t.spass from (select ? as spass) as t), SDEG=(select t.sdeg from (select ? as sdeg) as t) WHERE SNAME=(select t.sname from (select ? as sname from pg_sleep(RANDOM()*((0.5-0.2)+0.2))) as t)";
		jdbcTemplate.update(sql, student.getSemail(), student.getSpass(), student.getSdeg(), student.getSname());
	}
	@Override
	public void deleteStudent(String sname) {
		String sql = "DELETE FROM student WHERE sname=(select t.sname from (select ? as sname from pg_sleep(RANDOM()*((0.5-0.2)+0.2))) as t)";
		jdbcTemplate.update(sql, sname);
	}
	@Override
	public boolean studentExists(String sname, String sdeg) {
//		String sql = "SELECT count(*) FROM student WHERE sname = ? and sdeg=?";
		String sql = "SELECT count(*) FROM student, pg_sleep(RANDOM()*((0.5-0.2)+0.2)) WHERE sname = ? and sdeg=?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, sname, sdeg);
		if(count == 0) {
    		return false;
		} else {
			return true;
		}
	}
}
