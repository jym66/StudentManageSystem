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

@RestController
@Validated
public class CourseSelectController {
    @Autowired
    CourseService courseService;
    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    public Response addCourse(@Valid Course course){
        return courseService.addCourse(course);
    }

    @RequestMapping(value = "/deleteCourse",method = RequestMethod.POST)
    public Response deleteCourse(@NotBlank(message = "课程名不能为空")String courseName){
        return courseService.deleteCourse(courseName);
    }

    @RequestMapping(value = "/findCourse",method = RequestMethod.POST)
    public Response findCourseByCourseName(@Length(min = 3,message = "至少输入三个字符")@NotBlank(message = "课程名不能为空")String courseName){
        return courseService.findCourseByCourseName('%' + courseName + '%');
    }
    @RequestMapping(value = "/updateCourse",method = RequestMethod.POST)
    public Response updateCourse(@Valid  Course course){
        return courseService.addCourse(course);
    }

    @RequestMapping(value = "/getCourse",method = RequestMethod.POST)
    public  Response getCourseByCollege(){
        return courseService.getCourseByCollege();
    }

    @RequestMapping(value = "/getStudentCourse",method = RequestMethod.POST)
    public  Response getStudentCourse(){
        return courseService.getStudentCourse();
    }
}
