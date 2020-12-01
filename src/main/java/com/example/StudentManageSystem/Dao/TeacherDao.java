package com.example.StudentManageSystem.Dao;

import com.example.StudentManageSystem.pojo.StudentInfo;
import com.example.StudentManageSystem.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TeacherDao extends JpaRepository<Teacher,Integer> {
//    删除教师
    @Transactional
    void deleteByUserId(String userId);

//    根据userId查找教师
    List<Teacher> findByUserId(String userId);

    //分页
    @Query(value = "select * from Student.teacher limit ?,?",nativeQuery = true)
    List<Teacher> findAll(@Param("page") int page, @Param("limit") int limit);
//查询老师数量
    @Query(value = "SELECT COUNT(*) from Student.teacher",nativeQuery = true)
    int getCount();

}
