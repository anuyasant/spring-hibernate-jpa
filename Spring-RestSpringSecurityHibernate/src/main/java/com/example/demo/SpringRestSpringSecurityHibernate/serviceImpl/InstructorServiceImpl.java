package com.example.demo.SpringRestSpringSecurityHibernate.serviceImpl;

import com.example.demo.SpringRestSpringSecurityHibernate.dao.InstructorDao;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.Course;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.Instructor;
import com.example.demo.SpringRestSpringSecurityHibernate.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    private InstructorDao instructorDao;

    @Autowired
    public InstructorServiceImpl(InstructorDao instructorDao){
        this.instructorDao = instructorDao;
    }

    @Override
    @Transactional
    public List<Instructor> findAllInstructors() {
        return instructorDao.findAllInstructors();
    }

    @Override
    @Transactional
    public Instructor findById(int id) {
        return instructorDao.findById(id);
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        instructorDao.save(instructor);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        instructorDao.deleteById(id);
    }

    @Override
    @Transactional
    public List<Course> getCourseById(int id) {
        return instructorDao.getCourseById(id);
    }

    @Override
    @Transactional
    public String addCourseForInstructor(Course course, int instructorId) {
        return instructorDao.addCourseForInstructor(course, instructorId);
    }

    @Override
    @Transactional
    public String addCourseReview(Course course) {
        return instructorDao.addCourseReview(course);
    }

    @Override
    @Transactional
    public String addCourseStudent(Course course) {
        return instructorDao.addCourseStudent(course);
    }
}
