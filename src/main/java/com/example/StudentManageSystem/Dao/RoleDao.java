package com.example.StudentManageSystem.Dao;

import com.example.StudentManageSystem.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleDao extends JpaRepository<Role,Integer> {

    @Query(value = "select user_type from Student.login_info where username = :username ",nativeQuery = true)
    Integer getUserAuthority(@Param("username") String username);

    @Query(value = "select allow_user from Student.role where url = :url",nativeQuery = true)
    Integer getUrlAuthority(@Param("url") String url);

//    获取该用户可以访问的菜单
    @Query(value = "select * from Student.role where allow_user <=:allow and status !=0" ,nativeQuery = true)
    List<Role> getMenu(String allow);
}
