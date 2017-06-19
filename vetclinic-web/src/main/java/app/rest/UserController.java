package app.rest;

import dto.UserDto;
import entities.Animal;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
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

    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("email") String email){
        userService.delete(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody UserDto user){
        userService.add(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody UserDto user){
        userService.update(user);
    }

}
