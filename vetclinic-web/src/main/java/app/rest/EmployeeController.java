package app.rest;

import app.responses.BaseResponse;
import app.responses.ErrorResponse;
import app.responses.ErrorType;
import app.responses.SuccessResponse;
import dto.EmployeeDto;
import enums.Role;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.EmployeeService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/employee", produces="application/json")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EmployeeDto> getAll() {
        return employeeService.findAll();
    }

    @RequestMapping(value = "/{startPage}/{amount}",
            method = RequestMethod.GET)
    public List<EmployeeDto> getLimitEmployees(
            @PathVariable("startPage") Integer startPage,
            @PathVariable("amount") Integer amount) {
        return employeeService.getLimit(startPage, amount);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public EmployeeDto getSpecificEmployee(@PathVariable("email") String email) {
        return employeeService.findById(email);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public EmployeeDto getEmployeeInfo(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean accessRole = false;
        for (GrantedAuthority authority: authentication.getAuthorities()){
            if (!Objects.equals(authority.getAuthority(), Role.CLIENT.toString())){
                accessRole = true;
                break;
            }
        }
        if (accessRole){
            return employeeService.findById(authentication.getName());
        }

        return null;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long count(){
        return employeeService.count();
    }

    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse add(@RequestBody EmployeeDto employeeDto) {
        try {
            employeeService.add(employeeDto);
        } catch (ObjectAlreadyExistException e) {
            return new ErrorResponse(ErrorType.OBJECT_ALREADY_EXISTS);
        }

        return new SuccessResponse();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public BaseResponse update(@RequestBody EmployeeDto employeeDto) {
        try {
            employeeService.update(employeeDto);
        } catch (ObjectNotFoundException e) {
            return new ErrorResponse(ErrorType.USER_NOT_FOUND);
        }

        return new SuccessResponse();
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("email") String email) {
        employeeService.delete(email);

        return new SuccessResponse();
    }
}
