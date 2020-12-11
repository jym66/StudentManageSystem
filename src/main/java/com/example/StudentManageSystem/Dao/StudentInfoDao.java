package com.example.StudentManageSystem.Dao;

import com.example.StudentManageSystem.pojo.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentInfoDao extends JpaRepository<StudentInfo,Integer> {
//    单条数据

    List<StudentInfo> getByStudentId(String studentId);


    //    删除学生通过学生id
    void deleteByStudentId(String studentId);

//分页
    @Query(value = "select * from Student.student_info limit ?,?",nativeQuery = true)
    List<StudentInfo>findAll(@Param("page") int page, @Param("limit") int limit);

//    返回单条数据封装成对象
    @Query(value = "select * from Student.student_info where student_id=:userid",nativeQuery = true)
    StudentInfo getStudentInfo(@Param("userid") String userId);

    @Query(value = "SELECT COUNT(*) from Student.student_info",nativeQuery = true)
    int getCount();

    //    查询指定班级学生
    @Query(value = "select * from Student.student_info where class_name =:className limit :page,:limit",nativeQuery = true)
    List<StudentInfo> findByClassName(@Param("page") int page, @Param("limit") int limit,@Param("className") String className);

//根据班级查询学生数量
    @Query(value = "SELECT COUNT(*) from Student.student_info where class_name =:className ",nativeQuery = true)
    int getStudentCountByClassName(@Param("className") String className);

//    通过学号查询指定班级学生
    @Query(value = "SELECT * from Student.student_info where class_name =:className and student_id=:userId ",nativeQuery = true)
    List<StudentInfo> getStudentIdByClassName(@Param("userId") String userId,@Param("className") String className);
}