package com.mvc.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mvc.sms.payloads.StudentDto;
import com.mvc.sms.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping({"/","/students"})
	public String listOfStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents()); 
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		
		StudentDto StudentDto=new StudentDto();
		model.addAttribute("student",StudentDto);
		return "create_student";
	}
	
	
	@PostMapping("/students")
	public String addStudent(@ModelAttribute("student") StudentDto studentDto) {
		studentService.addStudent(studentDto);
		return "redirect:/students";
	}
	
	@GetMapping("/student/edit/{id}")
	public String editStudent(@PathVariable("id") Long sid, Model model) {
		model.addAttribute("student",studentService.getStudentById(sid));
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@ModelAttribute("student") StudentDto StudentDto,
			@PathVariable("id") Long sid,Model model){
	     studentService.updateStudent(StudentDto,sid);
		return "redirect:/students";
	}
	
	@GetMapping("/student/{id}")
	public String deleteStudent(@PathVariable("id") Long sid){
	     studentService.deleteStudentById(sid);
		return "redirect:/students";
	}
}
