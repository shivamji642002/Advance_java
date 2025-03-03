package com.ducat.dao;

import java.util.List;

import com.ducat.model.Student;

public interface StudentDao 
{
	int registerStudent(Student student);
	boolean updateStudent(Student student);
	boolean deleteStudent(int id);
	List<Student> getAllStudents();
	Student getStudentById(int id);
	Student getStudentByEmail(String email);
	Student validateUser(String email,String password);
	String[] getColumnsName();

}