package com.mvc.sms.service;

import java.util.List;

import com.mvc.sms.payloads.StudentDto;

public interface StudentService {
	
	List<StudentDto> getAllStudents();
	StudentDto addStudent(StudentDto studentDto);
	StudentDto getStudentById(Long id);
	StudentDto updateStudent(StudentDto studentDto, Long id);
	void deleteStudentById(Long id);

}
