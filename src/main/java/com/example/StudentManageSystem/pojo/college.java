package com.example.StudentManageSystem.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "college")
public class college {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "学院名不能为空")
    @Column(name = "college_name",unique = true)
    private String collegeName;


    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public college() {
    }

    public college(@NotBlank(message = "学院名不能为空") String collegeName) {
        this.collegeName = collegeName;
    }
}
