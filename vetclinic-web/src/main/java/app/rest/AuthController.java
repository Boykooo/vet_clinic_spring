package app.rest;


import app.entities.LoginForm;
import app.responses.BaseResponse;
import app.responses.ErrorResponse;
import app.responses.ErrorType;
import app.responses.TokenResponse;
import dto.EmployeeDto;
import entities.BaseUser;
import entities.Employee;
import enums.Role;
import enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.ClientRepository;
import repository.EmployeeRepository;
import security.TokenHandler;
import services.ClientService;
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
    private EmployeeRepository employeeRepository;
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public BaseResponse adminLogin(@RequestBody LoginForm loginForm) {
        if (UserUtils.defineUserType(loginForm.email) == UserType.CLIENT) {
            return new ErrorResponse(ErrorType.ACCESS_DENIED);
        }

        try {
            Employee employee = employeeRepository.findOne(loginForm.email);
            if (employee.getRole() != Role.ADMIN) {
                return new ErrorResponse(ErrorType.ACCESS_DENIED);
            }

            if (CryptManager.matchesPasswords(loginForm.password, employee.getPassword())) {
                return new TokenResponse(tokenHandler.generateAccessToken(loginForm.email, DateManager.getDateForToken()), UserType.EMPLOYEE);
            } else {
                return new ErrorResponse(ErrorType.INVALID_PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public BaseResponse userLogin(@RequestBody LoginForm loginForm) {
        try {
            BaseUser user;
            UserType userType = UserUtils.defineUserType(loginForm.email);

            user = (userType == UserType.CLIENT)
                    ? clientRepository.findOne(loginForm.email)
                    : employeeRepository.findOne(loginForm.email);

            if (CryptManager.matchesPasswords(loginForm.password, user.getPassword())) {
                String token = tokenHandler.generateAccessToken(loginForm.email, DateManager.getDateForToken());
                return new TokenResponse(token, userType);
            } else {
                return new ErrorResponse(ErrorType.INVALID_PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }
    }
}
