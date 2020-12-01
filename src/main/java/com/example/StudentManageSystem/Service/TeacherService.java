package com.example.StudentManageSystem.Service;

import com.example.StudentManageSystem.Dao.TeacherDao;
import com.example.StudentManageSystem.pojo.Response;
import com.example.StudentManageSystem.pojo.StudentInfo;
import com.example.StudentManageSystem.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherDao teacherDao;
    public Response addTeacher(Teacher teacher){
        if (teacherDao.findByUserId(teacher.getUserId()).size() != 0){
            return Response.fail("老师已存在");
        }
        teacher.setStatus(true);
        teacherDao.save(teacher);
        return Response.success("添加成功");

    }
    public Response deleteTeacherByUserNum(String userId){
        if (teacherDao.findByUserId(userId).size() == 0){
            return Response.fail("删除失败");
        }
        teacherDao.deleteByUserId(userId);
        return Response.success("删除成功");
    }
    public Response getTeacherByUserId(String userId){
        List<Teacher> teacher = teacherDao.findByUserId(userId);
//        if (teacher.size() == 0){
//            return Response.fail("老师不存在");
//        }
        return Response.success(teacher);
    }
    public Response getAllTeacherInfo(int page,int limit){
        if (limit > 70){ limit = 70;}
        return Response.success(teacherDao.findAll((page-1)*limit,limit));
    }

//    返回数据库老师总数
    public Response getTeacherCount(){
        return Response.success(teacherDao.getCount());
    }
    public Response updateTeacher(Teacher teacher){
        if (teacherDao.findByUserId((teacher.getUserId())).size() == 1){
            teacherDao.save(teacher);
            return Response.success("更新信息成功");
        }
        return Response.fail("更新信息失败");
    }
}
