package com.example.demo.SpringRestSpringSecurityHibernate.service;

import com.example.demo.SpringRestSpringSecurityHibernate.entity.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getCourses() ;

    Course getCourse(int courseId);

    Course addCourse(Course course);

    Course updateCourse(Course course);

    String deleteCourse(Course course);
}
