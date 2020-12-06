package com.example.StudentManageSystem.Service;

import com.example.StudentManageSystem.Dao.CourseDao;
import com.example.StudentManageSystem.pojo.Course;
import com.example.StudentManageSystem.pojo.Response;
import com.example.StudentManageSystem.pojo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;

    @Autowired
    StudentService studentService;

    public Response addCourse(Course course){
        if (checkCourseIsNull(course.getCourseId())){
            return Response.fail("课程已存在");
        }
        courseDao.save(course);
        return Response.success("操作成功");
    }

    public Response deleteCourse(String courseName){
        if (checkCourseIsNull(courseName)){
            courseDao.deleteByCourseName(courseName);
            return Response.success("删除成功");
        }
        return Response.fail("该课程不存在");
    }

    public Response findCourseByCourseName(String courseName){
//        if (!checkCourseIsNull(courseName)){
//            return Response.fail("未查询到该课程");
//        }
        return Response.success(courseDao.findByCourseNameLike(courseName));
    }

//    验证是否有该课程
    private boolean  checkCourseIsNull(String courseName){
        return courseDao.findByCourseName(courseName) != null;
    }

//    查询该学院课程表
    public Response getCourseByCollege(){
        StudentInfo studentInfo = studentService.getStudent();
        if (studentInfo == null){
            return Response.success(getAllCourse());
        }
        return Response.success(courseDao.getCourseByCollege(studentInfo.getCollage()));
    }
    private List<Course> getAllCourse(){
        System.out.println(courseDao.findAll());
        return courseDao.findAll();
    }
}
