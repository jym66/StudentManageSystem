package com.example.StudentManageSystem.Dao;

import com.example.StudentManageSystem.pojo.LoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;

public interface LoginInfoDao extends JpaRepository<LoginInfo,Integer> {
    @Query(value = "select * from Student.login_info where username = :name ",nativeQuery = true)
    LoginInfo checkPassword(@Param("name") String name);

    //    更新密码
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Student.login_info set password = :password where username = :username",nativeQuery = true)
    void updatePassword(@Param("password") String NewPassword, @Param("username") String userName);

//    添加用户
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT into Student.login_info (password, user_type, username) values (:password,:user_type,:username)",nativeQuery = true)
    void addUser(@Param("username") String username,@Param("password") String password,@Param("user_type") int userType);

//    删除用户
    void deleteByUsername(String username);
}
