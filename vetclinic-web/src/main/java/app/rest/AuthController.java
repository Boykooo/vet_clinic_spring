package app.rest;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", produces="application/json")
@CrossOrigin
public class AuthController {


    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public String adminPage() {

        return "hello";
    }
}
