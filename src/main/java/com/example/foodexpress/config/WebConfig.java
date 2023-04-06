package com.example.foodexpress.config;

import com.example.foodexpress.service.impl.WorkingTimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final WorkingTimeInterceptor workingTimeInterceptor;

    public WebConfig(WorkingTimeInterceptor workingTimeInterceptor) {
        this.workingTimeInterceptor = workingTimeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(workingTimeInterceptor);
    }

}
