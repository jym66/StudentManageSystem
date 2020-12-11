package com.example.StudentManageSystem.Controller;

import com.example.StudentManageSystem.Service.SelectCourseService;
import com.example.StudentManageSystem.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@RestController
@Validated
public class SelectCourseController {
    @Autowired
    SelectCourseService selectCourseService;
    @RequestMapping(value = "/getSelectCourse",method = RequestMethod.POST)
    public Response getSelectCourse(){
        return selectCourseService.getSelectCourse();
    }

    @RequestMapping(value = "/SelectCourse",method = RequestMethod.POST)
    public Response SelectCourse(@Valid @NotBlank(message = "课程Id不能为空") String courseId){
        return selectCourseService.SelectCourse(courseId);
    }


    @RequestMapping(value = "/deleteSelectCourse",method = RequestMethod.POST)
    public Response deleteCourse(@Valid @NotBlank(message = "课程Id不能为空")String courseId){
        return selectCourseService.deleteCourse(courseId);
    }


}
