package spring.fundamentals.exceptionHandling;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalLevel {
    @ExceptionHandler(CustomExceptionClass.class)
    public ResponseEntity<?> handleException(CustomExceptionClass e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(e.getStatusCode()));
    }
}
