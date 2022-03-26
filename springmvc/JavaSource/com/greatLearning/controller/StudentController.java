package com.greatLearning.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.model.Student;
import com.greatLearning.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String listBooks(Model theModel) {
		System.out.println("Request Recieved");
		List<Student> theStudents = studentService.getAllStudents();
		theModel.addAttribute("Students", theStudents);
		return "list-Students";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		// create model attribute to bind form data
		Student theStudent = new Student();
		theModel.addAttribute("Student", theStudent);
		return "Student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {

		Student theStudent = studentService.getStudent(theId);
		theModel.addAttribute("Student", theStudent);
		return "Student-form";
	}

	@PostMapping("/save")
	public String saveBook(@RequestParam("studentid") int id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {

		Student theStudent;
		if (id != 0) {
			//Update 
			theStudent = studentService.getStudent(id);
			theStudent.setName(name);
			theStudent.setdepartment(department);
			theStudent.setcountry(country);
		} else {
			//Create
			theStudent = new Student(name, department, country);
		}
		studentService.saveStudent(theStudent);
		return "redirect:/students/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		studentService.deleteStudent(theId);
		return "redirect:/students/list";
	}
}
