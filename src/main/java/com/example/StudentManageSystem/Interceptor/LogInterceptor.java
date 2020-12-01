package com.example.StudentManageSystem.Interceptor;

//登录拦截器
import com.example.StudentManageSystem.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 登录拦截器
 * 验证Token
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (Objects.equals(request.getMethod(), "OPTIONS")){
            return true;
        }
//        用户名加在uuid 后面间接解决HttpServletRequest 只能读取一个body的问题
        try {
            String AUTH = request.getHeader("Token_id");
            String userName = TokenUtil.findUserNameByToken(AUTH);
            String token = redisTemplate.opsForValue().get(userName);
            if (token == null){
                throw new RuntimeException("未登录或登录过期!");
            }
            redisTemplate.expire(userName,15 * 60 * 1000, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            throw new RuntimeException("未登录或登录过期!");
        }
        return true;
    }
}
