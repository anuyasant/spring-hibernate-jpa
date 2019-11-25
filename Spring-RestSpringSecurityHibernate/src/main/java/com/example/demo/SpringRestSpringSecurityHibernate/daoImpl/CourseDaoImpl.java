package com.example.demo.SpringRestSpringSecurityHibernate.daoImpl;

import com.example.demo.SpringRestSpringSecurityHibernate.dao.CourseDao;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private EntityManager entityManager;

    @Autowired
    public CourseDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Course> getCourses() {
        Query query = entityManager.createQuery(" from Course");
        List<Course> courseList = query.getResultList();
        return courseList;
    }

    @Override
    public Course getCourse(int courseId) {
        Course course = entityManager.find(Course.class, courseId);
        return course;
    }

    @Override
    public Course addCourse(Course course) {
        Course newCourse = saveCourse(course);
        return newCourse;
    }

    @Override
    public Course updateCourse(Course course) {
        Course updatedCourse = saveCourse(course);
        return updatedCourse;

    }

    @Override
    public String deleteCourse(Course course) {
        entityManager.remove(course);
        return "Course deleted successfully with ID - "+ course.getId();
    }

    private Course saveCourse(Course course){
        Course savedCourse = entityManager.merge(course);
        return savedCourse;
    }
}
