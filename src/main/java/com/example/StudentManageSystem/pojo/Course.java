package com.example.StudentManageSystem.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "课程名不能为空")
    @Column(name = "course_name")
    private String courseName;

//    @NotNull(message = "开设时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "time")
    private Date time;

    @NotBlank(message = "开设地点不能为空")
    @Column(name = "local")
    private String local;

    @NotBlank(message = "课程类型不能为空")
    @Column(name = "course_type" )
    private String courseType;
//    学分
    @Column
    @NotNull(message = "该课程学分不能为空")
    private Integer score;

    @NotBlank(message = "开设学院不能为空")
    @Column(name = "course_college")
    private String course_college;
    @NotBlank(message = "老师名不能为空")
    @Column(name = "teacher_name")
    private String TeacherName;

    @NotBlank(message = "老师工号不能为空")
    @Column(name = "teacher_num")
    private String TeacherNum;

    @NotBlank(message = "课程号不能为空")
    @Column(name = "course_id",unique = true)
    private String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public String getTeacherNum() {
        return TeacherNum;
    }

    public void setTeacherNum(String teacherNum) {
        TeacherNum = teacherNum;
    }

    public String getCourse_college() {
        return course_college;
    }

    public void setCourse_college(String course_college) {
        this.course_college = course_college;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
