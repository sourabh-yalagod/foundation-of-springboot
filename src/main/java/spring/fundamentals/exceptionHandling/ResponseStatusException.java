package spring.fundamentals.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "response status default reason.")
public class ResponseStatusException extends RuntimeException {
    HttpStatus status;

    public ResponseStatusException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
