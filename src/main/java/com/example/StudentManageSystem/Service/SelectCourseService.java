package com.example.StudentManageSystem.Service;

import com.example.StudentManageSystem.Dao.CourseDao;
import com.example.StudentManageSystem.Dao.SelectCourseDao;
import com.example.StudentManageSystem.Util.TokenUtil;
import com.example.StudentManageSystem.pojo.Course;
import com.example.StudentManageSystem.pojo.Response;
import com.example.StudentManageSystem.pojo.selectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SelectCourseService {
    @Resource
    HttpServletRequest request;

    @Autowired
    CourseDao courseDao;
    @Autowired
    SelectCourseDao selectCourseDao;
    private List<selectCourse> getSelectCourseId(){
        String token = request.getHeader("Token_id");
        String userId = TokenUtil.findUserNameByToken(token);
        return selectCourseDao.findByStudentId(userId);
    }

    public Response getSelectCourse(){
        List<selectCourse> courseId = getSelectCourseId();
        List<Course> courseList = new ArrayList<>();
        for (selectCourse Course : courseId) {
            courseList.add(courseDao.findByCourseId(Course.getCourseID()));
        }
        return Response.success(courseList);
    }

    public Response SelectCourse(String courseId){
        Course courses = courseDao.findByCourseId(courseId);
        if (courses == null){
            return Response.fail("课程不存在");
        }
        String token = request.getHeader("Token_id");
        String userId = TokenUtil.findUserNameByToken(token);
        if (selectCourseDao.checkCourse(courseId,userId) > 0){
            return Response.fail("请勿重复选课");
        }
            selectCourseDao.save(new selectCourse(courseId,userId));
        return Response.success("选课成功");
    }

    @Transactional
    public Response deleteCourse(String courseId){
        selectCourseDao.deleteByCourseID(courseId);
        return Response.success("取消成功！");
    }

    public  Response getSelectCourseIdByStudentId(String StudentId){
        List<selectCourse> selectCourses = selectCourseDao.findByStudentId(StudentId);
        List< Map<String,String>> list = new ArrayList<>();
        for (selectCourse selectCourse : selectCourses) {
            Map<String,String> stringMap = new HashMap<>();
            stringMap.put("courseName",courseDao.findByCourseId(selectCourse.getCourseID()).getCourseName());
            stringMap.put("courseId",courseDao.findByCourseId(selectCourse.getCourseID()).getCourseId());
            stringMap.put("studentId",StudentId);
            list.add(stringMap);
        }
        return Response.success(list);
    }
}
