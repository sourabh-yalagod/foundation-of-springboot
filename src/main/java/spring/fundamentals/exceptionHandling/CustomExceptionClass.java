package spring.fundamentals.exceptionHandling;

public class CustomExceptionClass extends RuntimeException {
    private int statusCode;

    public CustomExceptionClass(String message, int statusCode) {
        super(message); // Pass message to RuntimeException
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
