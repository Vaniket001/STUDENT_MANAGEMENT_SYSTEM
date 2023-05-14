package com.mvc.sms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.sms.entity.Student;
import com.mvc.sms.exception.ResourceNotFoundException;
import com.mvc.sms.payloads.StudentDto;
import com.mvc.sms.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		List<StudentDto> studentDtos= students
				.stream().map(student-> modelMapper.map(student, StudentDto.class))
				.collect(Collectors.toList());
		return studentDtos;
	}

	@Override
	public StudentDto addStudent(StudentDto studentDto) {
		Student student= modelMapper.map(studentDto, Student.class);
		Student savedStudent=studentRepository.save(student);
		return modelMapper.map(savedStudent, StudentDto.class);
	}

	@Override
	public StudentDto getStudentById(Long studentId) {
		Student student=this.studentRepository.findById(studentId)
				.orElseThrow(()->new ResourceNotFoundException("Student","Id",studentId));
		StudentDto studentDto=this.modelMapper.map(student, StudentDto.class);
		return studentDto;
	}

	@Override
	public StudentDto updateStudent(StudentDto studentDto, Long studentId) {
		Student student=this.studentRepository.findById(studentId)
				.orElseThrow(()->new ResourceNotFoundException("Student","Id",studentId));
		
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setEmail(studentDto.getEmail());
		studentRepository.save(student);
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);
	}
}
