package com.example.demo.SpringRestSpringSecurityHibernate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;


    //Bidirectional relationship. Hence, Join column where FK is present. i.e. in Course table
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "instructor_id")
    @JsonBackReference
    private Instructor instructor;

    //Delete all reviews if course is deleted
    //Unidirectional relationship. Hence, Join column in this entity,
    //foreign key is present in review.
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> review;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "course_student",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    //@JsonManagedReference
    private List<Student> studentList;

    public Course(){

    }

    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    /*@Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructor=" + instructor +
                ", review=" + review +
                ", studentList=" + studentList +
                '}';
    }*/

    public void addReview(Review review){
        if(this.review == null) {
            this.review = new ArrayList<Review>();
        }

        this.review.add(review);
    }

    public void addStudent(Student student){
        if(this.studentList == null) {
            this.studentList = new ArrayList<Student>();
        }

        this.studentList.add(student);
    }


}
