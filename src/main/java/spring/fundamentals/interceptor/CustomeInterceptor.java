package spring.fundamentals.interceptor;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("/api")
public class CustomeInterceptor {
    @GetMapping("/spring/fundamentals/interceptor")
    public String getResponse() {
        System.out.println("controller");
        return "Hello from Interceptor Endpoint!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin Endpoint (excluded from interceptor)";
    }
}
@Component
class CustomHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("PreHandle: Request URL -> " + request.getRequestURI());
        return true; // Continue processing
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, @Nullable ModelAndView modelAndView) {
        System.out.println("PostHandle: After controller but before view rendering");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("AfterCompletion: Request completed");
    }
}
@Configuration
class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private CustomHandlerInterceptor customHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customHandlerInterceptor)
                .addPathPatterns("/api/**")      // Intercept all /api requests
                .excludePathPatterns("/api/admin"); // Skip this path
    }
}