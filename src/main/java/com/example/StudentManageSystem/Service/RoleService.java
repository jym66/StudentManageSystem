package com.example.StudentManageSystem.Service;

import com.example.StudentManageSystem.Dao.RoleDao;
import com.example.StudentManageSystem.pojo.Response;
import com.example.StudentManageSystem.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 角色权限查询
 */
@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    @Cacheable("UserAuthority")
    public Integer getUserAuthority(String username){
        return roleDao.getUserAuthority(username);
    }
    @Cacheable("UrlAuthority")
    public Integer getUrlAuthority(String url){
        return roleDao.getUrlAuthority(url);
    }

    public Response getMenu(String allow){
        List<Role> AllMenu = roleDao.getMenu(allow);
        List<Role> menus = new ArrayList<>();
        for (Role role : AllMenu) {
            if (role.getUrl() == null) {
                menus.add(role);
            }
        }

        return Response.success(getChildMenus(menus,AllMenu,allow));
    }
    private List<Role> getChildMenus(List<Role> OneMenu,List<Role> AllMenu,String allow){
        String student = "student";
        String admin = "admin";
        String str = "teacher";
        if (Objects.equals(allow, "3")){
            str = admin;
        }
        if (Objects.equals(allow, "1")){
             str = student;
        }

        for (Role oneMenu : OneMenu) {
            //子菜单先用ArrayList 存起来 ，如果直接添加会覆盖掉原来的
//            在for 循环里创建动态数组 达到每次清空的目的
            List<Role> ChildMenus = new ArrayList<>();
            for (Role allMenu : AllMenu) {
//                不等于null 说明是一个子菜单
                if (allMenu.getParentId() != null) {
//                   查找子菜单对应的父菜单
                    if (Objects.equals(allMenu.getParentId(), String.valueOf(oneMenu.getId())) && Objects.equals(allMenu.getShowUser(),str)) {
                         ChildMenus.add(allMenu);
                    }
                }
            }
//           执行完内层for循环说明当前菜单掉子菜单都找到了,添加
            oneMenu.setChildMenus(ChildMenus);
        }
        return OneMenu;
        }
    }
