package com.example.StudentManageSystem.Controller;

import com.example.StudentManageSystem.Service.CourseService;
import com.example.StudentManageSystem.pojo.Course;
import com.example.StudentManageSystem.pojo.Response;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

//课程类的增删改查
@RestController
@Validated
public class CourseController {
    @Autowired
    CourseService courseService;
    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
//    增加课程
    public Response addCourse(@Valid Course course){
        return courseService.addCourse(course);
    }

    @RequestMapping(value = "/deleteCourse",method = RequestMethod.POST)
//    删除课程
    public Response deleteCourse(@NotBlank(message = "课程名不能为空")String courseName){
        return courseService.deleteCourse(courseName);
    }

    @RequestMapping(value = "/findCourse",method = RequestMethod.POST)
//    查找课程
    public Response findCourseByCourseName(@Length(min = 3,message = "至少输入三个字符")@NotBlank(message = "课程名不能为空")String courseName){
        return courseService.findCourseByCourseName('%' + courseName + '%');
    }
    @RequestMapping(value = "/updateCourse",method = RequestMethod.POST)
//    更新课程
    public Response updateCourse(@Valid  Course course){
        return courseService.addCourse(course);
    }

    @RequestMapping(value = "/getCourse",method = RequestMethod.POST)
//    通过token_id返回课程
    public  Response getCourseByCollege(){
        return courseService.getCourseByCollege();
    }
//查询改学生所在学院的课程
    @RequestMapping(value = "/getStudentCourse",method = RequestMethod.POST)
    public  Response getStudentCourse(){
        return courseService.getStudentCourse();
    }





}
