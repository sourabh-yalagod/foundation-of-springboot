package spring.fundamentals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest-controller")
public class ControllerConfig {
    @GetMapping("")
    public String getResponse() {
        return "GotResponse[rest-controller]";
    }
}


@Controller
@RequestMapping("/api/controller")
class ControllerClasss {
    @ResponseBody
    @GetMapping("")
    public String getResponse() {
        return "GotResponse[controller]";
    }
}
