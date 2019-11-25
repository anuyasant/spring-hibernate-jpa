package com.example.demo.SpringRestSpringSecurityHibernate.controller;

import com.example.demo.SpringRestSpringSecurityHibernate.entity.Instructor;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.InstructorDetail;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

public class SpringBootRestTestClient {
    private static final String SPRING_REST_URI = "http://localhost:8080/instructor";
    private static RestTemplate restTemplate;

    private static void listAllInstructors(){
        restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> instructorMap = restTemplate.getForObject(SPRING_REST_URI+"/get_instructor/", List.class);

        if(instructorMap!=null){
            for(LinkedHashMap<String, Object> map : instructorMap){
                System.out.println("User : id="+map.get("id")+
                ", Name= "+map.get("firstName")+", Lastname= "+map.get("lastName")+", Email="+map.get("email"));;

            }
        }else {
            System.out.println("No user exist----------");
        }
    }

    private static void listInstructor(){
        restTemplate = new RestTemplate();
        Instructor instructor = restTemplate.getForObject(SPRING_REST_URI+"/get_instructor/1", Instructor.class);

        if(instructor!=null){
            System.out.println(instructor.getInstructorDetail().getHobby());
        }else {
            System.out.println("No user exist----------");
        }
    }

    private static void createInstructor() {
        System.out.println("Testing create User API----------");
        restTemplate = new RestTemplate();
        Instructor instructor = new Instructor("Sarah","Connor","sarah@gmail.com");
        URI uri = restTemplate.postForLocation(SPRING_REST_URI+"/add_instructor/", instructor, Instructor.class);

    }

    private static void updateInstructor() {
        System.out.println("Testing Update User API----------");
        restTemplate = new RestTemplate();
        InstructorDetail instructorDetail = new InstructorDetail("SarahConnor@youtube", "movies");
        Instructor instructor = restTemplate.getForObject(SPRING_REST_URI+"/get_instructor/4", Instructor.class);
        System.out.println(instructor);
        instructor.setInstructorDetail(instructorDetail);
        restTemplate.put(SPRING_REST_URI+"/update_instructor/", instructor);
    }


    private static void deleteInstructor() {
        System.out.println("Testing Delete Instructor API----------");
        restTemplate = new RestTemplate();
        restTemplate.delete(SPRING_REST_URI+"/delete_instructor/7");
    }

    public static void main(String args[]){

        listInstructor();
        createInstructor();
        //updateInstructor();
        deleteInstructor();
        listAllInstructors();
    }
}
