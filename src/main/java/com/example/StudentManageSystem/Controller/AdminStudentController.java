package com.example.StudentManageSystem.Controller;

import com.example.StudentManageSystem.Service.StudentService;
import com.example.StudentManageSystem.pojo.Response;
import com.example.StudentManageSystem.pojo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author python
 * 查询学生信息类
 * 管理员可操作的权限
 * 其他人不可操作
 */
@RestController
@Validated
public class AdminStudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
//  获取学生信息
    public Response getStudentInfo(@NotBlank(message = "学号不能为空") String studentId){
        return studentService.getStudentInfo(studentId);
    }

    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
//    添加学生
    public Response addStudent(@Valid StudentInfo studentInfo){
        return studentService.addStudent(studentInfo);
    }

    @RequestMapping(value = "/deleteStudent",method = RequestMethod.POST)
//    删除学生
    public Response deleteStudentByStudentId(@NotBlank(message = "学号不能为空")String studentId){
        return studentService.deleteStudentByStudentID(studentId);
    }
    @RequestMapping(value = "/getAllStudentInfo",method = RequestMethod.POST)
//    获取全部学生信息
    public Response getAllStudentInfo(int page,int limit){
        return studentService.getAllStudentInfo(page,limit);
    }
    @RequestMapping(value = "/getCount",method = RequestMethod.POST)
//    获取学生数量
    public Response getCount(){
        return studentService.getCount();
    }

    @RequestMapping(value = "/updateStudent",method = RequestMethod.POST)
//    修改学生信息
    public Response updateStudent(@Valid StudentInfo studentInfo){
        return studentService.updateStudent(studentInfo);
    }

    @RequestMapping(value = "/getNotice",method = RequestMethod.POST)
//    发布班级通知(老师)
    public Response getNotice(){
        return studentService.getNotice();
    }


}
