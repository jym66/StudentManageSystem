package com.example.StudentManageSystem.pojo;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student_score")
public class StudentScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @NotBlank(message = "课程号不允许为空")
    @Column(name ="course_id")
    private String courseId;
    @NotBlank(message = "学号不允许为空")
    @Column(name = "student_id")
    private String studentId;

    @NotNull(message = "成绩不能为空")
    @Range(min = 0,max = 100,message = "只能为0到100之间！！！")
    @Column(name = "score")
    private Integer score;
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public StudentScore(String courseId, String studentId,Integer score) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.score = score;

    }

    public StudentScore() {
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
