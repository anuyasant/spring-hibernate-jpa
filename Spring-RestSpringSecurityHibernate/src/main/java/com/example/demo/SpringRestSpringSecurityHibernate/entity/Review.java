package com.example.demo.SpringRestSpringSecurityHibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name="review")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "comment")
    private String comment;

    public Review() {
    }

    public Review(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /*@Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }*/
}
