package com.example.StudentManageSystem.Dao;

import com.example.StudentManageSystem.pojo.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Classname ScoreDao
 * @Description TODO
 * @Date 2020/12/12 7:26 下午
 * @Created by python
 */
public interface ScoreDao extends JpaRepository<StudentScore,Integer> {
    List<StudentScore> findByStudentId(String studentId);

    StudentScore findByCourseId(String courseId);
}
