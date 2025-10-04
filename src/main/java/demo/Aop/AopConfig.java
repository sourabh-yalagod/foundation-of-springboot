package demo.Aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopConfig {
    @Before("execution(public org.springframework.http.ResponseEntity demo.Aop.AopApi.getResponseFromAop())")
    public void beforeLogs(){
        System.out.println("AopConfig logs before");
    }
    @After("execution(public org.springframework.http.ResponseEntity demo.Aop.AopApi.getResponseFromAop())")
    public void afterLogs(){
        System.out.println("AopConfig logs after");
    }
}
