package com.example.StudentManageSystem.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "selectCourse")
public class selectCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;


    @Column(name = "courseID")
    private String courseID;

    @Column(name = "studentId")
    private String studentId;

    public selectCourse() {
    }

    public selectCourse(String courseID, String studentId) {
        this.courseID = courseID;
        this.studentId = studentId;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}

