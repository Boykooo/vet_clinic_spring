package app.rest;


import app.entities.LoginForm;
import app.responses.BaseResponse;
import app.responses.ErrorResponse;
import app.responses.ErrorType;
import app.responses.TokenResponse;
import dto.EmployeeDto;
import enums.Role;
import enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import security.TokenHandler;
import services.EmployeeService;
import util.CryptManager;
import util.DateManager;
import util.UserUtils;

@RestController
@RequestMapping(value = "/auth", produces = {"application/json"})
@CrossOrigin
public class AuthController {

    @Autowired
    private TokenHandler tokenHandler;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public BaseResponse employeeLogin(@RequestBody LoginForm loginForm) {
        if (UserUtils.defineUserType(loginForm.email) == UserType.USER){
            return new ErrorResponse(ErrorType.ACCESS_DENIED);
        }

        try {
            EmployeeDto employee = employeeService.findById(loginForm.email);
            if (employee.getRole() != Role.ADMIN){
                return new ErrorResponse(ErrorType.ACCESS_DENIED);
            }

            if (CryptManager.matchesPasswords(loginForm.password, employee.getPassword())){
                return new TokenResponse(tokenHandler.generateAccessToken(loginForm.email, DateManager.getDateForToken()));
            } else {
                return new ErrorResponse(ErrorType.INVALID_PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }
    }
}
