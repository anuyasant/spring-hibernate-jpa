package com.example.demo.SpringRestSpringSecurityHibernate.dao;

import com.example.demo.SpringRestSpringSecurityHibernate.entity.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getCourses();

    Course getCourse(int courseId);

    Course addCourse(Course course);

    Course updateCourse(Course course);

    String deleteCourse(Course course);
}
