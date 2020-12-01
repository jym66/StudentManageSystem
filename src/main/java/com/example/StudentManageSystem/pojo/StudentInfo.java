package com.example.StudentManageSystem.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


//根据pojo规则定义实体类 首字母要小写要不然jpa无法注入
@Entity
@Table(name = "student_info")
public class StudentInfo  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Range(min = 10,message = "该学生需要满10岁")
    @NotNull(message = "年龄不能为空")
    @Column(name = "age")
    private Integer age;

    @NotNull(message = "性别不能为空")
    @Range(min = 0,max = 1,message = "性别填写错误")
    @Column(length = 1,name = "sex")
    private Integer sex;

    @NotBlank(message = "手机号不能为空")
    @Column(nullable = false,name = "phone_number")
    private String phoneNumber;

    @Column(name = "home_address")
    private String homeAddress;
    @NotBlank(message = "姓名不能为空")
    @Column(nullable = false,name = "student_name")
    private String studentName;

    @NotBlank(message = "学院不能为空")
    @Column(nullable = false,name = "collage")
    private String collage;

    @NotBlank(message = "班级不能为空")
    @Column(nullable = false,name = "class_name")
    private String className;

    @NotBlank(message = "学号不能为空")
    @Column(nullable = false,name = "student_id",unique = true)
    private String studentId;

    @Column(name = "birthday",columnDefinition = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Column(name ="school_time",columnDefinition = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date school_time;

    @Column(name = "status")
    private boolean status;

    public int getId() {
        return Id;
    }

    public Date getSchool_time() {
        return school_time;
    }

    public void setSchool_time(Date school_time) {
        this.school_time = school_time;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}