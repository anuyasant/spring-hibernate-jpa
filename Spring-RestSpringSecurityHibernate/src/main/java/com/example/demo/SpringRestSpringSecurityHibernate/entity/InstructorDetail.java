package com.example.demo.SpringRestSpringSecurityHibernate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "instructor_detail")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String channel;

    @Column(name = "hobby")
    private String hobby;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    @JsonBackReference
    private Instructor instructor;

    public InstructorDetail(){

    }

    public InstructorDetail(String channel, String hobby) {
        this.channel = channel;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /*@Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", channel='" + channel + '\'' +
                ", hobby='" + hobby + '\'' +
                ", instructor=" + instructor +
                '}';
    }*/
}
