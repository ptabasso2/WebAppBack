package com.pejman.client;

import com.pejman.entity.Student;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClientUtil {

    /*public void getArticleByIdDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/student/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Student> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Student.class, 1);
        Student student = responseEntity.getBody();
        System.out.println("Id:"+ student.getArticleId()+", Title:"+ student.getSname()
                 +", Category:"+ student.getSdeg());
    }*/

	public void getAllArticlesDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/students";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Student[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Student[].class);
        Student[] students = responseEntity.getBody();
        for(Student student : students) {
              /*System.out.println("Id:"+ student.getArticleId()+", Title:"+ student.getSname()
                      +", Category: "+ student.getSdeg());*/
            System.out.println("Student name:" + student.getSname()+ ", Student degree: " + student.getSdeg());
        }
    }

    /*public void addArticleDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/article";
	    Student objStudent = new Student();
	    objStudent.setArticleId(8);
	    objStudent.setSname("Spring REST Security using Hibernate0000000");
	    objStudent.setSdeg("Spring");
        HttpEntity<Student> requestEntity = new HttpEntity<Student>(objStudent, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }*/


    /*public void updateArticleDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/article";
	    Student objStudent = new Student();
	    objStudent.setArticleId(1);
	    objStudent.setSname("Update:Java Concurrency");
	    objStudent.setSdeg("Java");
        HttpEntity<Student> requestEntity = new HttpEntity<Student>(objStudent, headers);
        restTemplate.put(url, requestEntity);
    }*/

    public void deleteArticleDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/article/{id}";
        HttpEntity<Student> requestEntity = new HttpEntity<Student>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 1);        
    }

    public static void main(String args[]) {
    	RestClientUtil util = new RestClientUtil();
        //util.getArticleByIdDemo();
    	//util.addArticleDemo();
    	//util.updateArticleDemo();
    	//util.deleteArticleDemo();
    	util.getAllArticlesDemo();
    }    
}
