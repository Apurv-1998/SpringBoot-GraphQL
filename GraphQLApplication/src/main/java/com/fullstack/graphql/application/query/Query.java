package com.fullstack.graphql.application.query;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fullstack.graphql.application.request.SampleRequest;

/*This class act as @RestController
 * No Controller Class As in the case of Rest API
 * All methods in GraphQL are HTTP POST unlike Rest 
 * */

@Component
public class Query implements GraphQLQueryResolver {
	
	
	/*
	 * No separate URL for each and every application
	 * The function is mapped with the help of the schema .graphqls files in the classpath
	 * */
	
	public String firstQuery() {
		return "First Query";
	}
	
	public String secondQuery() {
		return "Second Query";
	}
	
	public String thirdQuery(String a,String b) {
		return Integer.toString((Integer.parseInt(a)*100)|(Integer.parseInt(b)*Integer.parseInt(a)*1000));
	}
	
	public String fullName(SampleRequest sample) {
		
		return sample.getFirstName() +" "+ sample.getLastName();
		
	}
}
