package com.niit.dao;

import java.util.List;

import com.niit.model.Student;

public interface StudentDao {
	List<Student> getAllStudents();
	Student addStudent(Student student);
	Student getStudentById(int id);
	void deleteStudent(int id);
	void updateStudent(Student student);
	}
