package usermanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String getRoot(){
        return "{\"user controller \" : \"/users\"," +
                "\"user type controller \" : \"/usertypes\"," +
                "\"swagger ui \" : \"/swagger-ui.html\"," +
                "\"swagger doc\" : \"/v2/api-docs\"" +
                "}";
    }
}