package com.example.demo.SpringRestSpringSecurityHibernate.service;

import com.example.demo.SpringRestSpringSecurityHibernate.entity.Course;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.Instructor;

import java.util.List;

public interface InstructorService {
    public List<Instructor> findAllInstructors();

    public Instructor findById(int id);

    public void save(Instructor instructor);

    public void deleteById(int id);

    public List<Course> getCourseById(int id);

    String addCourseForInstructor(Course course, int instructorId);

    String addCourseReview(Course course);

    String addCourseStudent(Course course);
}
