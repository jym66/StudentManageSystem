package com.example.StudentManageSystem.Controller;

import com.example.StudentManageSystem.Service.TeacherService;
import com.example.StudentManageSystem.pojo.Response;
import com.example.StudentManageSystem.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "/addTeacher",method = RequestMethod.POST)
    public Response addTeacher(@Valid  Teacher teacher){
        return teacherService.addTeacher(teacher);
    }
    @RequestMapping(value = "/deleteTeacher",method = RequestMethod.POST)

    public Response deleteTeacher(@NotBlank(message = "工号不能为空")String id){
        return teacherService.deleteTeacherByUserNum(id);
    }

    @RequestMapping(value = "/getTeacher",method = RequestMethod.POST)
    public Response getTeacherByUserId(@NotBlank(message = "工号不能为空") String UserId){
        return teacherService.getTeacherByUserId(UserId);
    }

    @RequestMapping(value = "/getAllTeacherInfo",method = RequestMethod.POST)
    public Response getAllTeacherInfo(int page,int limit){
        return teacherService.getAllTeacherInfo(page,limit);
    }
    @RequestMapping(value = "/getTeacherCount",method = RequestMethod.POST)
    public Response getTeacherCount(){
        return teacherService.getTeacherCount();
    }
    @RequestMapping(value = "/updateTeacher",method = RequestMethod.POST)
    public Response updateTeacher(@Valid Teacher teacher){
        return teacherService.updateTeacher(teacher);
    }

}
