package app.rest;

import app.responses.BaseResponse;
import app.responses.ErrorResponse;
import app.responses.ErrorType;
import app.responses.SuccessResponse;
import dto.UserDto;
import entities.Animal;
import entities.User;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces="application/json")
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
    public BaseResponse delete(@PathVariable("email") String email) {
        userService.delete(email);

        return new SuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse add(@RequestBody UserDto user) {
        try {
            userService.add(user);
        } catch (ObjectAlreadyExistException e) {
            return new ErrorResponse(ErrorType.OBJECT_ALREADY_EXISTS);
        }

        return new SuccessResponse();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public BaseResponse update(@RequestBody UserDto user) {
        try {
            userService.update(user);
        } catch (ObjectNotFoundException e) {
            return new ErrorResponse(ErrorType.USER_NOT_FOUND);
        }

        return new SuccessResponse();
    }
}
