package demo.Profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ProfileAnnotation {
    private final String BASE_URL;
    private final String env;

    public ProfileAnnotation(@Value("${baseurl}") String baseUrl,@Value("${env}") String env) {
        this.env=env;
        this.BASE_URL=baseUrl;
        System.out.println(this.BASE_URL + " in " + this.env + " Environment");
    }
}
