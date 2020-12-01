package com.example.StudentManageSystem.Interceptor;

import com.example.StudentManageSystem.Service.RoleService;
import com.example.StudentManageSystem.Util.TokenUtil;
import com.example.StudentManageSystem.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 *
 * 角色权限拦截器
 * 验证角色是否有权限访问
 */
@Component
public class RoleInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    RoleService roleService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (Objects.equals(request.getMethod(), "OPTIONS")){
            return true;
        }
        String token = request.getHeader("token_id");
        String userName = TokenUtil.findUserNameByToken(token);
        String url = request.getRequestURI();
//        查询当前用户权限
        Integer Authority = roleService.getUserAuthority(userName);
//        查询当前访问url所需权限
        Integer urlAuthority =roleService.getUrlAuthority(url);
        if (urlAuthority == null){
            throw new RuntimeException("测试性功能(暂未开放,敬请期待)");
        }

        if (Authority < urlAuthority){
            throw new RuntimeException("权限不足");
        }
        return true;
    }
}
