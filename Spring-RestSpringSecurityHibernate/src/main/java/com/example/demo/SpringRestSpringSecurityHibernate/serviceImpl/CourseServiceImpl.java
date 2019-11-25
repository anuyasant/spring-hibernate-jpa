package com.example.demo.SpringRestSpringSecurityHibernate.serviceImpl;

import com.example.demo.SpringRestSpringSecurityHibernate.dao.CourseDao;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.Course;
import com.example.demo.SpringRestSpringSecurityHibernate.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao){
        this.courseDao = courseDao;
    }

    @Override
    @Transactional
    public List<Course> getCourses() {
        return courseDao.getCourses();
    }

    @Override
    @Transactional
    public Course getCourse(int courseId) {
        return courseDao.getCourse(courseId);
    }

    @Override
    @Transactional
    public Course addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    @Transactional
    public Course updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    @Override
    @Transactional
    public String deleteCourse(Course course) {
        return courseDao.deleteCourse(course);
    }
}
