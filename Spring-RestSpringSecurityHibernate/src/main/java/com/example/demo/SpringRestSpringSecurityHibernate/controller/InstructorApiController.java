package com.example.demo.SpringRestSpringSecurityHibernate.controller;

import com.example.demo.SpringRestSpringSecurityHibernate.entity.Course;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.Instructor;
import com.example.demo.SpringRestSpringSecurityHibernate.service.InstructorService;
import com.example.demo.SpringRestSpringSecurityHibernate.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorApiController {
    public static final Logger logger = LoggerFactory.getLogger(InstructorApiController.class);

    private InstructorService instructorService;
    @Autowired
    public InstructorApiController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/get_instructor")
    public ResponseEntity<List<Instructor>> getAllInstructors(){

        List<Instructor> instructors = instructorService.findAllInstructors();
        if(instructors == null){
            return new ResponseEntity("No instructor details found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Instructor>>(instructors,HttpStatus.OK);
    }

    @GetMapping("/get_instructor/{id}")
    @ResponseBody
    public ResponseEntity<?> getInstructorById(@PathVariable int id){
        Instructor instructor = instructorService.findById(id);
        if(instructor == null){
            //return new ResponseEntity<String>("Instructor with Id "+id+ " not found", HttpStatus.NOT_FOUND);
            return new ResponseEntity(new CustomErrorType("Instructor with Id "+id+ " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Instructor>(instructor, HttpStatus.OK);
    }

    @PostMapping("/add_instructor")
    public Instructor saveInstructor(@RequestBody Instructor instructor){
        instructor.setId(0);
        instructorService.save(instructor);
        return instructor;
    }

    @PutMapping("/update_instructor")
    public Instructor updateInstructor(@RequestBody Instructor instructor){
        instructorService.save(instructor);
        return instructor;
    }

    @DeleteMapping("/delete_instructor/{id}")
    public String deleteInstructor(@PathVariable int id){
        Instructor instructor = instructorService.findById(id);
        if(instructor == null){
            throw new RuntimeException("Instructor id not found - " + id);
        }
        instructorService.deleteById(id);
        return "Instructor details deleted successfully for Id "+ id;
    }

    @GetMapping("/get_course/{id}")
    public List<Course> getCourseForInstructor(@PathVariable int id){
        Instructor instructor = instructorService.findById(id);
        if(instructor == null){
            throw new RuntimeException("Instructor id not found - " + id);
        }
        List<Course> course = instructorService.getCourseById(id);
        return course;
    }

    @PostMapping("/add_course")
    public void addCourseForInstructor(@RequestBody List<Course> courses){
        for(Course course : courses){
            Instructor instructor = course.getInstructor();
            if(instructor == null){
                throw new RuntimeException("Instructor id not found - ");
            }
            //instructor.addCourse(temp);
            String result = instructorService.addCourseForInstructor(course, instructor.getId());

        }
    }

    @PostMapping("/add_course_review")
    //public void addCourseReview(@RequestBody List<Review> reviews, int courseId){
    public void addCourseReview(@RequestBody Course course){
        String result = instructorService.addCourseReview(course);
    }

    @PostMapping("/add_course_student")
    public void addCourseStudent(@RequestBody Course course){
        String result = instructorService.addCourseStudent(course);
    }

}
