package com.example.StudentManageSystem.config;

import com.example.StudentManageSystem.Interceptor.LogInterceptor;
import com.example.StudentManageSystem.Interceptor.RoleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//添加拦截器
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public LogInterceptor getLogInterceptor() {
        return new LogInterceptor();
    }

    @Bean
    public RoleInterceptor getRoleInterceptor(){
        return new RoleInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLogInterceptor()).excludePathPatterns("/login");
        registry.addInterceptor(getRoleInterceptor()).excludePathPatterns("/login");
    }
}
