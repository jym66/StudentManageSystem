package com.example.StudentManageSystem.Service;

import com.example.StudentManageSystem.Dao.NoticeDao;
import com.example.StudentManageSystem.Dao.StudentInfoDao;
import com.example.StudentManageSystem.Dao.TeacherDao;
import com.example.StudentManageSystem.Util.TokenUtil;
import com.example.StudentManageSystem.pojo.Notice;
import com.example.StudentManageSystem.pojo.Response;
import com.example.StudentManageSystem.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherDao teacherDao;
    @Resource
    HttpServletRequest request;
    @Autowired
    StudentInfoDao studentInfoDao;
    @Autowired
    NoticeDao noticeDao;
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

    private List<Teacher> getTeacher(){
//        获取老师实体
        String token = request.getHeader("Token_id");
        String userId = TokenUtil.findUserNameByToken(token);
        return teacherDao.findByUserId(userId);
    }
    public Response ReleaseClassNotice(String message){
//        首先查询老师所带班级
        String className = getTeacher().get(0).getClassName();
        noticeDao.save(new Notice(className,message));
        return Response.success("发布通知成功");
    }
//    获取老师自己班的学生
    public Response getStudent(int page,int limit){
        if (limit > 70){ limit = 70;}
        String className = getTeacher().get(0).getClassName();
        return Response.success(studentInfoDao.findByClassName(page,limit,className));
    }

    public Response getStudentById(String userId){
//        通过学号查询指定班级学生
        return Response.success(studentInfoDao.getStudentIdByClassName(userId,getTeacher().get(0).getClassName()));
    }

    public Response getStudentCountByClassName() {
        return Response.success(studentInfoDao.getStudentCountByClassName(getTeacher().get(0).getClassName()));
    }
}
