package app.rest;

import entities.Animal;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public User getSpecificUser(@PathVariable("email") String email) {
        return repository.findOne(email);
    }

}
