package app.rest;


import app.entity.LoginForm;
import dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import security.TokenHandler;
import services.EmployeeService;
import util.CryptManager;
import util.DateManager;

@RestController
@RequestMapping(value = "/auth", produces = "application/json")
@CrossOrigin
public class AuthController {

    @Autowired
    private TokenHandler tokenHandler;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String employeeLogin(@RequestBody LoginForm loginForm) {

        try {

            EmployeeDto employee = employeeService.findById(loginForm.email);
            if (CryptManager.matchesPasswords(loginForm.password, employee.getPassword())){
                return tokenHandler.generateAccessToken(loginForm.email, DateManager.getDateForToken());
            } else {
                //TODO: throw exception or send bad response
            }
        } catch (Exception e) {

        }

        return null;
    }
}
