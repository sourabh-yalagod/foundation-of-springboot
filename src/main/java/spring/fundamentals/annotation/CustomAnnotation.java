package spring.fundamentals.annotation;

@GetName(name = "Sourabh")
public class CustomAnnotation {

}

@interface GetName {
    String name() default "Spring Boot";
}
