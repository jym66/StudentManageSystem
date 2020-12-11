package com.example.StudentManageSystem.Dao;

import com.example.StudentManageSystem.pojo.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Classname NoticeDao
 * @Description TODO
 * @Date 2020/12/10 8:33 下午
 * @Created by python
 */
public interface NoticeDao extends JpaRepository<Notice,Integer> {
    List<Notice> findByClassName(String className);
}
