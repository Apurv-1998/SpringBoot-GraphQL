package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.repository.AddressRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.CreateSubjectRequest;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	public Student getStudentById (long id) {
		return studentRepository.findById(id).get();
	}
	
	public Student createStudent(CreateStudentRequest createStudentRequest) {
		
		Student student = new Student(createStudentRequest);
		
		Address address = new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());
		
		address = addressRepository.save(address);
		
		student.setAddress(address);
		
		List<Subject> subjectList = new ArrayList<>();
		
		if(createStudentRequest.getSubjectLearnings()!=null && createStudentRequest.getSubjectLearnings().size()>0) {
			for(CreateSubjectRequest subjectRequest : createStudentRequest.getSubjectLearnings()) {
				Subject subject = new Subject();
				subject.setSubjectName(subjectRequest.getSubjectName());
				subject.setMarksObtained(subjectRequest.getMarksObtained());
				
				subject = subjectRepository.save(subject);
				
				subjectList.add(subject);
			}
		}
		
		student.setLearningSubjects(subjectList);
		
		return studentRepository.save(student);
		
	}
}
