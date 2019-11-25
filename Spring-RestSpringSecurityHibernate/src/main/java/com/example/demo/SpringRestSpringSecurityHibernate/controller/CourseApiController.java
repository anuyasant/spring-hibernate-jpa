package com.example.demo.SpringRestSpringSecurityHibernate.controller;

import com.example.demo.SpringRestSpringSecurityHibernate.entity.Course;
import com.example.demo.SpringRestSpringSecurityHibernate.service.CourseService;
import com.example.demo.SpringRestSpringSecurityHibernate.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/course")
public class CourseApiController {

    public static final Logger logger = LoggerFactory.getLogger(CourseApiController.class);

    private CourseService courseService;

    @Autowired
    public CourseApiController(CourseService courseService){
        this.courseService = courseService;
    }
    @RequestMapping("/course")
    public ResponseEntity<List<Course>> getCourses(){
        List<Course> courseList = courseService.getCourses();
        if(courseList == null){
            return new ResponseEntity(new CustomErrorType("No Course List found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Course>> (courseList, HttpStatus.OK);
    }

    @RequestMapping("/course/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable int courseId){
        Course course = courseService.getCourse(courseId);
        if(course == null){
            return new ResponseEntity(new CustomErrorType("No Course found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Course> (course, HttpStatus.OK);
    }

    @PostMapping("/course")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        course.setId(0);
        Course courseAdded = courseService.addCourse(course);
        return new ResponseEntity<Course> (courseAdded, HttpStatus.OK);
    }

    @PutMapping("/course")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course){
        Course courseUpdated = courseService.updateCourse(course);
        return new ResponseEntity<Course> (courseUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable int courseId){
        Course course = courseService.getCourse(courseId);
        if(course == null){
            return new ResponseEntity(new CustomErrorType("Course not found with Id " + courseId), HttpStatus.NOT_FOUND);
        }
        String message = courseService.deleteCourse(course);
        return new ResponseEntity<String> (message, HttpStatus.OK);
    }
}
