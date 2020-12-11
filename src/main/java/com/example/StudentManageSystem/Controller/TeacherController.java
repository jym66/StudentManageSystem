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
//    添加老师
    public Response addTeacher(@Valid  Teacher teacher){
        return teacherService.addTeacher(teacher);
    }
    @RequestMapping(value = "/deleteTeacher",method = RequestMethod.POST)
//删除老师
    public Response deleteTeacher(@NotBlank(message = "工号不能为空")String id){
        return teacherService.deleteTeacherByUserNum(id);
    }

    @RequestMapping(value = "/getTeacher",method = RequestMethod.POST)
//    通过工号获取老师
    public Response getTeacherByUserId(@NotBlank(message = "工号不能为空") String UserId){
        return teacherService.getTeacherByUserId(UserId);
    }

    @RequestMapping(value = "/getAllTeacherInfo",method = RequestMethod.POST)
//    查询全部老师
    public Response getAllTeacherInfo(int page,int limit){
        return teacherService.getAllTeacherInfo(page,limit);
    }
    @RequestMapping(value = "/getTeacherCount",method = RequestMethod.POST)
    public Response getTeacherCount(){
        return teacherService.getTeacherCount();
    }
    @RequestMapping(value = "/updateTeacher",method = RequestMethod.POST)
//    修改老师信息
    public Response updateTeacher(@Valid Teacher teacher){
        return teacherService.updateTeacher(teacher);
    }

    @RequestMapping(value = "/sendClassNotice",method = RequestMethod.POST)
    //    发布班级通知
    public Response ReleaseClassNotice(@NotBlank(message = "请不要发布空消息") String message){
        return teacherService.ReleaseClassNotice(message);
    }

    @RequestMapping(value = "/getAllStudentByClassName",method = RequestMethod.POST)
//    通过班级获取全部学生
    public Response getStudent(int page ,int limit){
        return teacherService.getStudent(page,limit);
    }

    @RequestMapping(value = "/getStudentCountByClassName",method = RequestMethod.POST)
//    通过班级获取学生数量
    public Response getStudentCountByClassName(){
        return teacherService.getStudentCountByClassName();
    }

    @RequestMapping(value = "/getStudentByClassName",method = RequestMethod.POST)
//    通过id查询当前老师所带班级的学生
    public Response getStudentByClassName(@NotBlank(message = "学号不允许为空") String userId){
        return teacherService.getStudentById(userId);
    }
}
