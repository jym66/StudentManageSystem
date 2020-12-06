package com.example.StudentManageSystem.Service;

import com.example.StudentManageSystem.Dao.LoginInfoDao;
import com.example.StudentManageSystem.Dao.StudentInfoDao;
import com.example.StudentManageSystem.Dao.TeacherDao;
import com.example.StudentManageSystem.Util.MD5Util;
import com.example.StudentManageSystem.Util.TokenUtil;
import com.example.StudentManageSystem.pojo.LoginInfo;
import com.example.StudentManageSystem.pojo.Response;
import com.example.StudentManageSystem.pojo.StudentInfo;
import com.example.StudentManageSystem.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author python
 */
@Service
public class LoginService {
    @Resource
    HttpServletRequest request;
    @Autowired
    private LoginInfoDao loginInfoDao;
    @Autowired
    private StudentInfoDao studentInfoDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource
    private HttpServletRequest httpServletRequest;

    private  final int Admin = 3;
    private  final String defaultPassword = "12345678";
    //    添加用户
    public void addUser(String username,String password,int userType){
        loginInfoDao.addUser(username,password,userType);
    }

    private boolean CheckUserNameAndPassword(String user,String Password){
        LoginInfo login = findPasswordByUserName(user);
        if (login == null || !Objects.equals(login.getPassword(), Password)){
            return false;
        }
       return Objects.equals(Password, login.getPassword());
    }
    private LoginInfo findPasswordByUserName(String user){
        return loginInfoDao.checkPassword(user);
    }
    public Response checkLoginInfo(LoginInfo loginInfo){
        if (CheckUserNameAndPassword(loginInfo.getUsername(),MD5Util.getMD5(loginInfo.getPassword()))){
            String token = TokenUtil.getToken(loginInfo.getUsername());
            addTokenRedis(loginInfo.getUsername(),token);
            return Response.success("登录成功",token);
        }
        if (findStudentIdByLoginInfo(loginInfo.getUsername())){
            int student = 1;
            if (!Objects.equals(loginInfo.getPassword(), defaultPassword)){
                return Response.fail(403,"账号密码错误");
            }
            addUser(loginInfo.getUsername(),MD5Util.getMD5(defaultPassword), student);
            return checkLoginInfo(loginInfo);
        }
        if (findTeacherIdByLoginInfo(loginInfo.getUsername())){
            int teacher = 2;
            if (!Objects.equals(loginInfo.getPassword(), defaultPassword)){
                return Response.fail(403,"账号密码错误");
            }
            addUser(loginInfo.getUsername(),MD5Util.getMD5(defaultPassword), teacher);
            return checkLoginInfo(loginInfo);
        }
        return Response.fail(403,"账号密码错误");
    }
//      登出
    public Response logout(){
        deleteToken(request.getHeader("Token_id"));
        return Response.success("登出成功!","");
    }
    private void addTokenRedis(String token,String userName){
        redisTemplate.opsForValue().set(token,userName);
        int ex_time = 15 * 60 * 1000;
        redisTemplate.expire(token, ex_time, TimeUnit.MILLISECONDS);
    }
    private void deleteToken(String token){
        redisTemplate.delete(TokenUtil.findUserNameByToken(token));
    }
//    管理员直接修改密码
    public Response forgetPassword(String NewPassword) {
        String token = httpServletRequest.getHeader("token_id");
        if (token == null){
            return Response.fail("修改失败,请联系管理员");
        }
        String username = TokenUtil.findUserNameByToken(token);
        String Md5NewPassword = MD5Util.getMD5(NewPassword);
        loginInfoDao.updatePassword(Md5NewPassword,username);
        return Response.success("成功");
    }

//    去Student表查有没有此学号
    private boolean findStudentIdByLoginInfo(String username){
        return studentInfoDao.getByStudentId(username).size() != 0  ;
    }
//    去Teacher表查有没有此学号
    private boolean findTeacherIdByLoginInfo(String userId){
        return teacherDao.findByUserId(userId).size() !=0;
    }

//    删除登录信息
    public void DeleteUser(String username){
        loginInfoDao.deleteByUsername(username);
    }

    public Response getSelfInfo(){
        String AUTH = request.getHeader("Token_id");
        String userName = TokenUtil.findUserNameByToken(AUTH);
        List<StudentInfo> studentInfo = studentInfoDao.getByStudentId(userName);
        if (studentInfo.size() != 0){
            return Response.success(studentInfo.get(0).getStudentName() + "(学生)");
        }
        List<Teacher> teachers = teacherDao.findByUserId(userName);
        if (teachers.size() != 0){
            return Response.success(teachers.get(0).getUserName() + "(老师)");
        }
        return Response.success(userName + "（管理员）");
    }
}

