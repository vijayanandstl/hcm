package tech.stl.hcm.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import tech.stl.hcm.common.interceptor.LoggingInterceptor;
import tech.stl.hcm.common.interceptor.RateLimitInterceptor;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Value("${cors.allowed.origins:*}")
    private List<String> allowedOrigins;
    
    @Value("${cors.allowed.methods:GET,POST,PUT,DELETE,OPTIONS}")
    private List<String> allowedMethods;
    
    @Value("${cors.allowed.headers:*}")
    private List<String> allowedHeaders;

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Autowired
    private RateLimitInterceptor rateLimitInterceptor;
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins.toArray(new String[0]))
                .allowedMethods(allowedMethods.toArray(new String[0]))
                .allowedHeaders(allowedHeaders.toArray(new String[0]));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
        registry.addInterceptor(rateLimitInterceptor);
    }
} 