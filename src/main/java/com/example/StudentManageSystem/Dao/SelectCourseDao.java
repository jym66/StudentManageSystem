package com.example.StudentManageSystem.Dao;

import com.example.StudentManageSystem.pojo.selectCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SelectCourseDao extends JpaRepository<selectCourse,Integer> {
    List<selectCourse> findByStudentId(String studentId);

//    判断选课重复
    @Query(value = "select  exists(select * from Student.select_course where student_id = :studentId and courseid=:courseId) ",nativeQuery = true)
    int checkCourse(@Param("courseId") String courseId, @Param("studentId") String studentId);

//    删除课程
    void deleteByCourseID(String courseId);
}
