package spring.fundamentals.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Aspect
@Component
public class Aop {
    @Before("execution(* spring.fundamentals.aop.AopTestingApi.*(..))")
    public void logsBeforeApi() {
        System.out.println("Before /api/aop");
    }

    @After("execution(* spring.fundamentals.aop.AopTestingApi.*(..))")
    public void logsAfterApi() {
        System.out.println("After /api/aop");
    }

    @Around("execution(* spring.fundamentals.aop.AopTestingApi.*(String))")
    public void logsThrowingApi(ProceedingJoinPoint jointPoint) throws Throwable {
        System.out.println("Before /api/aop");
        jointPoint.proceed();
        System.out.println("After /api/aop");
    }
}

@RestController
@RequestMapping("/api/aop")
class AopTestingApi {
    @GetMapping("/{name}")
    public String getName(@PathVariable(name = "name") String name) throws Exception {
        System.out.println("Method Executed");
        return "Hi " + name;
    }

    @GetMapping("")
    public String getResponse() {
        return "Hi ";
    }
}