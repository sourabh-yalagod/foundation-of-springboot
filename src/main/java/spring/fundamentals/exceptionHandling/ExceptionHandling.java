package spring.fundamentals.exceptionHandling;

import jdk.jshell.Snippet;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exception")
public class ExceptionHandling {
    @GetMapping("")
    public ResponseEntity<?> getResponse() {
        return new ResponseEntity<>(new CustomException("Custom Error java"), HttpStatus.BAD_GATEWAY);
    }
}
// Exception -> handlerExceptionResolverComposite -> 3 resolvers [ExceptionHandlerExceptionResolver,ResponseStatusExceptionResolver,defaultHandlerExceptionResolver]
// if non-of the resolver can handle the exception then it is handled by default Error Attribute.
// defaultErrorAttribute actually create response for exception.


class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}

// 1] exceptionHandlerExceptionResolver responsible for handling annotation like @exceptionHandler , @ControllerAdvice
