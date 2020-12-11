package com.example.StudentManageSystem.Controller;

import com.example.StudentManageSystem.Service.LoginService;
import com.example.StudentManageSystem.pojo.LoginInfo;
import com.example.StudentManageSystem.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;


/**
 * @author python
 * 登录类
 */
@RestController
//@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService ;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    登录
    public Response login(@Valid LoginInfo loginInfo){
        return loginService.checkLoginInfo(loginInfo);
    }
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
//    登出
    public Response logout(){
        return loginService.logout();
    }
    @RequestMapping(value = "/forgetPassword",method = RequestMethod.POST)
//    忘记密码
    public Response forgetPassword(String userName,String NewPassword){
        return loginService.forgetPassword(NewPassword);
    }
    @RequestMapping(value = "/getSelfInfo",method = RequestMethod.POST)
//    获取自己的信息
    public Response getSelfInfo(){
        return loginService.getSelfInfo();
    }


}
