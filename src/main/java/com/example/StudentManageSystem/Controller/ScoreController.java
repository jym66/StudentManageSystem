package com.example.StudentManageSystem.Controller;

import com.example.StudentManageSystem.Service.ScoreService;
import com.example.StudentManageSystem.Service.SelectCourseService;
import com.example.StudentManageSystem.pojo.Response;
import com.example.StudentManageSystem.pojo.StudentScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@RestController
@Validated
public class ScoreController {
    @Autowired
    ScoreService scoreService;
    @Autowired
    SelectCourseService selectCourseService;
    @RequestMapping(value = "/addScore",method = RequestMethod.POST)
    public Response addScore(@Valid StudentScore studentScore){
        return scoreService.addScore(studentScore);
    }
    @RequestMapping(value = "/getSelectCourseIdByStudentId",method = RequestMethod.POST)
    public Response getSelectCourseIdByStudentId(@NotBlank(message = "学号不允许为空") String studentId){
        return selectCourseService.getSelectCourseIdByStudentId(studentId);
    }

    @RequestMapping(value = "/getSelfScore",method = RequestMethod.POST)
    public Response getSelfScore(){
        return scoreService.getSelfScore();
    }
}
