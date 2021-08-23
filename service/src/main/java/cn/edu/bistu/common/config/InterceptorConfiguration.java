package cn.edu.bistu.common.config;

import cn.edu.bistu.common.interceptor.TokenAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    ApplicationContext applicationContext;

    public <T> T getMyInterceptors(Class<T> clazz) {
        T bean = applicationContext.getBean(clazz);
        return bean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor tokenAuthInterceptor = getMyInterceptors(TokenAuthInterceptor.class);
        registry.addInterceptor(tokenAuthInterceptor)
                .excludePathPatterns("/auth/login")
                .excludePathPatterns("/auth/userInfoCompletion");
        ;
    }
}
