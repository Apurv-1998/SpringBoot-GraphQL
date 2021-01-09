package com.example.resolver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.entity.Subject;
import com.example.enums.SubjectNameFilter;
import com.example.response.StudentResponse;
import com.example.response.SubjectResponse;

/*
 * Only when the data is demanded then only show
 * Every method should be public
 * */

@Service
public class StudentResponseResolver implements GraphQLResolver<StudentResponse> {
	
	//We will create get method for learning subjects
	
	//GraphQL will automatically come to this method we do not need to call this method manually
	
	//We will also apply filtering on the subject -> SubjectName enum
	
	public List<SubjectResponse> getLearningSubjects(StudentResponse studentResponse, SubjectNameFilter subjectNameFilter){
		
		List<SubjectResponse> learningSubjects = new ArrayList<>();
		
		if(studentResponse.getStudent().getLearningSubjects() != null) {
			for(Subject subject : studentResponse.getStudent().getLearningSubjects()) {
				
				//If consumer demands all subjects -> Skip filtering
				if(subjectNameFilter.name().equalsIgnoreCase("All")) {
					learningSubjects.add(new SubjectResponse(subject));
				}
				//Filter
				else if(subjectNameFilter.name().equalsIgnoreCase(subject.getSubjectName())) {
					learningSubjects.add(new SubjectResponse(subject));
				}
				
			}
		}
		
		return learningSubjects;
		
	}
	
	public String getFullName(StudentResponse studentResponse) {
		
		return studentResponse.getFirstName() +" "+studentResponse.getLastName();
		
	}

}
