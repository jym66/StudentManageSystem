package com.example.StudentManageSystem.Controller;

import com.example.StudentManageSystem.Service.RoleService;
import com.example.StudentManageSystem.Util.TokenUtil;
import com.example.StudentManageSystem.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class Menu {
    @Autowired
    RoleService roleService;

    @Resource
    HttpServletRequest request;
    @RequestMapping(value = "/getmenu",method = RequestMethod.POST)
//    获取菜单
    public Response getMenu(){
        String token = request.getHeader("Token_id");
        String username = TokenUtil.findUserNameByToken(token);
        String allow = String.valueOf(roleService.getUserAuthority(username));
        return roleService.getMenu(allow);
    }
}
