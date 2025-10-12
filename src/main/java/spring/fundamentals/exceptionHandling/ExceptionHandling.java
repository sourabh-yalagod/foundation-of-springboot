package spring.fundamentals.exceptionHandling;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/exception")
public class ExceptionHandling {
    @GetMapping("")
    public ResponseEntity<?> getResponse() {
        throw new CustomExceptionClass("Custom Error java", 401);
    }

    @ExceptionHandler(CustomExceptionClass.class)
    public void exceptionHandler(CustomExceptionClass e, HttpServletResponse response) throws IOException {
        response.sendError(e.getStatusCode(), e.getMessage());
    }
}
// Exception -> handlerExceptionResolverComposite -> 3 resolvers [ExceptionHandlerExceptionResolver,ResponseStatusExceptionResolver,defaultHandlerExceptionResolver]
// if non-of the resolver can handle the exception then it is handled by default Error Attribute.
// defaultErrorAttribute actually create response for exception.

// 1] exceptionHandlerExceptionResolver responsible for handling annotation like @exceptionHandler , @ControllerAdvice
