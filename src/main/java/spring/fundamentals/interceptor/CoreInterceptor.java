package spring.fundamentals.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("/api")
public class CoreInterceptor {
    @GetMapping("/user")
    public ResponseEntity<String> getResponse() {
        return ResponseEntity.ok("Controller response");
    }
}

@Configuration
class CodeInterceptorConfig implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Before Reaching to Controller");
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("After Reaching to Controller");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}

@Configuration
class RegisterCodeInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    CodeInterceptorConfig codeInterceptorConfig;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(codeInterceptorConfig)
                .addPathPatterns("/api/*")
                .excludePathPatterns("/admin");
    }
}
