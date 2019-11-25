package com.example.demo.SpringRestSpringSecurityHibernate.daoImpl;

import com.example.demo.SpringRestSpringSecurityHibernate.dao.InstructorDao;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.Course;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.Instructor;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.Review;
import com.example.demo.SpringRestSpringSecurityHibernate.entity.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class InstructorDaoImpl implements InstructorDao {

    // define field for entitymanager
    private EntityManager entityManager;

    //constructor injection
    @Autowired
    public InstructorDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Instructor> findAllInstructors() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query =
                currentSession.createQuery(" from Instructor ", Instructor.class);
        List<Instructor> instructors = query.getResultList();
        return instructors;
    }

    @Override
    public Instructor findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Instructor instructor = currentSession.get(Instructor.class, id);
        return instructor;
    }

    @Override
    public void save(Instructor instructor) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(instructor);
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Instructor instructor = currentSession.get(Instructor.class, id);
        currentSession.delete(instructor);
    }

    @Override
    public List<Course> getCourseById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Instructor instructor = currentSession.get(Instructor.class, id);
        return instructor.getCourses();
    }

    @Override
    public String addCourseForInstructor(Course course, int instructorId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Instructor instructor = currentSession.get(Instructor.class, instructorId);
        instructor.addCourse(course);
        course.setInstructor(instructor);
        currentSession.saveOrUpdate(course);
        //currentSession.saveOrUpdate(instructor);
        return "Course added successfully.";
    }

    @Override
    public String addCourseReview(Course course) {
        Session currentSession = entityManager.unwrap(Session.class);
        Course thisCourse = currentSession.get(Course.class, course.getId());
        for(Review review : course.getReview())
            thisCourse.addReview(review);
        currentSession.saveOrUpdate(thisCourse);
        return "Review added successfully!";
    }

    @Override
    public String addCourseStudent(Course course) {
        Session currentSession = entityManager.unwrap(Session.class);
        Course thisCourse = currentSession.get(Course.class, course.getId());
        for(Student student : course.getStudentList()){
            thisCourse.addStudent(student);
        }
        currentSession.saveOrUpdate(thisCourse);
        return "Students added successfully!";
    }
}
