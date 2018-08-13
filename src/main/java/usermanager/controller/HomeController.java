package usermanager.controller;

import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public Object getRoot() {
        HashMap urls = new HashMap();
        urls.put("user controller", "/users/");
        urls.put("user type conroller", "/usertypes/");
        urls.put("swagger ui","/swagger-ui.html");
        urls.put("swagger doc", "/v2/api-docs/");

        HashMap auths = new HashMap();

        auths.put("admin role",new String[]{"/users/","/usertypes/"});
        auths.put("user role",new String[]{});
        auths.put("public", new String[]{"/swagger-ui.html","/v2/api-docs/","/"});

        HashMap response = new HashMap();

        response.put("urls", urls);
        response.put("Authenticatin", auths);
        response.put("status", 200);
        response.put("message", "welcome to user manager API");
        
        return response;
    }
}