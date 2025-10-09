package spring.fundamentals.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.*;

@RestController
@RequestMapping("/api")
class InterPretorController {
    @Autowired
    private User user;

    @GetMapping("/interpretor")
    public String getResponse() {
        return user.getUser();
    }
}

@Component
class User {
    @CustomInterfaceForInterceptor(name = "sourabh")
    public String getUser() {
        System.out.println("Inside getUser()");
        return "GetUserAskedName";
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CustomInterfaceForInterceptor {
    String name() default "";
}

@Configuration
@EnableAspectJAutoProxy
@Aspect
class InterceptorConfig {
    @Around("@annotation(annotation)")
    public Object runInterceptor(ProceedingJoinPoint joinPoint, CustomInterfaceForInterceptor annotation) throws Throwable {
        System.out.println("Interceptor Ran for name: " + annotation.name());
        Object result = joinPoint.proceed();
        System.out.println("After method execution");
        return result;
    }
}
