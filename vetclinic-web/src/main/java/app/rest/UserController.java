package app.rest;

import dto.UserDto;
import entities.Animal;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public UserDto getSpecificUser(@PathVariable("email") String email) {
        return userService.findById(email);
    }

}
