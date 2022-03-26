package com.greatLearning.service;

import java.util.List;

import com.greatLearning.model.Student;

public interface StudentService {

	void saveStudent(Student student);

	List<Student> getAllStudents();

	Student getStudent(int id);

	void deleteStudent(int id);

}
