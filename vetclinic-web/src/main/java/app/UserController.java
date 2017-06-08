package app;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping("/test")
    public String process(){
        return "Done";
    }

    @RequestMapping("/get")
    public Iterable<User> test(){
        return repository.findAll();
    }

}
