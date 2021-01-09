package com.example.request;

import java.util.List;

import lombok.Data;

@Data
public class CreateStudentRequest {
	
	private String firstName;
	private String lastName;
	private String email;
	private String street;
	private String city;
	
	private List<CreateSubjectRequest> subjectLearnings;

}
