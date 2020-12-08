package com.example.StudentManageSystem.Dao;

import com.example.StudentManageSystem.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseDao extends JpaRepository<Course,Integer> {
    @Transactional

//    通过课程名字删除一整条记录
    void deleteByCourseName(String CourseName);

//    @Query(value = "select * from Student.course where course_name like %:CourseName%",nativeQuery = true)
//    List<Course> findCourseByCourseName(@Param("CourseName") String CourseName);


    List<Course> findByCourseNameLike(String CourseName);

    Course findByCourseName(String CourseName);

    Course findByCourseId(String CourseName);

//    void updateCourse(Course course);

    @Query(value = "select * from Student.course where course_college=:courseName",nativeQuery = true)
    List<Course> getCourseByCollege(@Param("courseName") String courseName);
}
