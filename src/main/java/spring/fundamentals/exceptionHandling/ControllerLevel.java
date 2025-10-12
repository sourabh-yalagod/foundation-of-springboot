package spring.fundamentals.exceptionHandling;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exception/controller")
public class ControllerLevel {
    @GetMapping("")
    public ResponseEntity<?> getResponse() {
        throw new CustomExceptionClass("custom throw", 401);
    }

    @ExceptionHandler(CustomExceptionClass.class)
    public ResponseEntity<Object> handleException(CustomExceptionClass e) {
        return new ResponseEntity<>(new ErrorObject(e.getMessage(), e.getStatusCode()), HttpStatusCode.valueOf(e.getStatusCode()));
    }
}

class ErrorObject {
    private String message;
    private int statusCode;

    public ErrorObject(String message, int statusCode) {
        this.statusCode = statusCode;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
