package com.example.StudentManageSystem.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "工号不允许为空")
    @Column(name = "user_id",unique = true,nullable = false)
    private String userId;

    @Column(name = "user_name")
    @NotBlank(message = "姓名不允许为空")
    private String userName;

    @Range(min = 28,message = "年龄至少28岁")
    @NotNull(message = "年龄不能为空")
    @Column(name = "age")
    private Integer age;


    @NotNull(message = "性别不能为空")
    @Column(name = "sex")
    @Range(min = 0,max = 1,message = "性别填写错误")
    private Integer sex;
//    学历
    @Column(name = "degree")
    @NotBlank(message = "学历不允许为空")
    private String degree;

//    职称
    @Column(name = "title")
    @NotBlank(message = "职称不允许为空")
    private String title;

//    入职时间
    @Column(name = "grade",columnDefinition = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date grade;

    @NotBlank(message = "学院不允许为空")
    @Column(name = "college")
    private String college;

    @NotBlank(message = "请分配该老师所带班级")
    @Column(name = "class_name")
    private String className;

    @Column(name = "status")
    private boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getGrade() {
        return grade;
    }

    public void setGrade(Date grade) {
        this.grade = grade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
