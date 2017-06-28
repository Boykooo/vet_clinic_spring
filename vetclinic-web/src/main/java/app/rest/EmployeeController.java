package app.rest;

import dto.EmployeeDto;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.EmployeeService;

import java.util.List;

/**
 * Created by andrey on 08.06.17.
 */

@RestController
@RequestMapping(value = "/employee", produces="application/json")
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

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long count(){
        return employeeService.count();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody EmployeeDto employeeDto) {
        employeeService.add(employeeDto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody EmployeeDto employeeDto) {
        employeeService.update(employeeDto);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("email") String email) {
        employeeService.delete(email);
    }
}
