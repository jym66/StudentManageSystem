package com.example.StudentManageSystem.Service;

import com.example.StudentManageSystem.Dao.CourseDao;
import com.example.StudentManageSystem.Dao.ScoreDao;
import com.example.StudentManageSystem.Dao.StudentInfoDao;
import com.example.StudentManageSystem.Util.TokenUtil;
import com.example.StudentManageSystem.pojo.Course;
import com.example.StudentManageSystem.pojo.Response;
import com.example.StudentManageSystem.pojo.StudentInfo;
import com.example.StudentManageSystem.pojo.StudentScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ScoreService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    ScoreDao scoreDao;
    @Autowired
    StudentInfoDao studentInfoDao;
    @Resource
    HttpServletRequest request;
    public Response addScore(StudentScore studentScore){
//        Course course = courseDao.findByCourseId(studentScore.getCourseId());
        List<StudentInfo> studentInfo = studentInfoDao.getByStudentId(studentScore.getStudentId());
        StudentScore studentScore1 = scoreDao.findByCourseId(studentScore.getCourseId());
        if (studentInfo == null){
            return Response.fail("失败");
        }
        if (studentScore1!=null){
            return Response.fail("该课程已打过分！！！");
        }
        scoreDao.save(studentScore);
        return Response.success("成功");
    }

    public Response getSelfScore(){
        List<Map<String,String>> result= new ArrayList<>();
        String token = request.getHeader("Token_id");
        String userId = TokenUtil.findUserNameByToken(token);
        List<StudentScore> scores =scoreDao.findByStudentId(userId);
        for (StudentScore score : scores) {
            Map<String,String> map = new HashMap<>();
            map.put("courseName",courseDao.findByCourseId(score.getCourseId()).getCourseName());
            map.put("courseId",score.getCourseId());
            map.put("score",String.valueOf(score.getScore()));
            result.add(map);
        }
        return Response.success(result);
    }
}
