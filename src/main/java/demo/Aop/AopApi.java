package demo.Aop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aop")
public class AopApi {

    @GetMapping("")
    public ResponseEntity<String> getResponseFromAop(){
        return ResponseEntity.ok("responseFromAop");
    }
}
