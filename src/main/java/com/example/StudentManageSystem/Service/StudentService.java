package com.example.StudentManageSystem.Service;

import com.example.StudentManageSystem.Dao.NoticeDao;
import com.example.StudentManageSystem.Dao.StudentInfoDao;
import com.example.StudentManageSystem.Util.TokenUtil;
import com.example.StudentManageSystem.pojo.Response;
import com.example.StudentManageSystem.pojo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

/**
 * @author python
 *
 */
@Service
public class StudentService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    StudentInfoDao studentInfoDao;

    @Autowired
    LoginService loginService;
    @Resource
    HttpServletRequest request;

    @Autowired
    NoticeDao noticeDao;
    public Response getStudentInfo(String studentId){
        return Response.success(studentInfoDao.getByStudentId(studentId));
    }
    public Response addStudent(StudentInfo studentInfo){
        if (studentInfoDao.getByStudentId((studentInfo.getStudentId())).size() ==0){
            studentInfo.setStatus(true);
            studentInfoDao.save(studentInfo);
            return Response.success("添加成功");
        }
        if (studentInfoDao.getByStudentId((studentInfo.getStudentId())).size() !=0){
            return Response.fail("该学生已存在");
        }
        return Response.fail("操作失败");
    }

    @Transactional
    public Response deleteStudentByStudentID(String studentId){
        if (studentInfoDao.getByStudentId(studentId) == null){
            return Response.fail("用户不存在");
        }
        studentInfoDao.deleteByStudentId(studentId);
//        再去redis 缓存里删除一下以免还能继续登录
        redisTemplate.delete(studentId);
//        再把登录信息删了
        loginService.DeleteUser(studentId);
        if (studentInfoDao.getByStudentId(studentId).size() == 0){
            return Response.success("删除成功");
        }
        return Response.fail("删除失败");
    }
    public Response getAllStudentInfo(int page,int limit){
        if (limit > 70){ limit = 70;}
        return Response.success(studentInfoDao.findAll((page-1)*limit,limit));
    }

//    返回数据库学生总数
    public Response getCount(){
        return Response.success(studentInfoDao.getCount());
    }


    public StudentInfo getStudent(){
        String token = request.getHeader("Token_id");
        String userId = TokenUtil.findUserNameByToken(token);
        if (studentInfoDao.getByStudentId(userId).size() == 0) {
            return null;
        }
        return  studentInfoDao.getByStudentId(userId).get(0);
    }
    public Response updateStudent(StudentInfo studentInfo){
        if (studentInfoDao.getByStudentId((studentInfo.getStudentId())).size() != 0){
            studentInfoDao.save(studentInfo);
            return Response.success("更新信息成功");
        }
        return Response.fail("更新信息失败");
    }

//    获取班级通知
    public Response getNotice(){
        String token = request.getHeader("Token_id");
        String userId = TokenUtil.findUserNameByToken(token);
        String className = studentInfoDao.getByStudentId(userId).get(0).getClassName();
        return Response.success(noticeDao.findByClassName(className));
    }
}
