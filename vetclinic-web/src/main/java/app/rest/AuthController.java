package app.rest;


import forms.LoginForm;
import app.responses.BaseResponse;
import app.responses.ErrorResponse;
import app.responses.ErrorType;
import app.responses.TokenResponse;
import entities.BaseUser;
import entities.Employee;
import enums.Role;
import enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import dao.ClientDao;
import dao.EmployeeDao;
import security.TokenHandler;
import util.CryptManager;
import util.DateManager;
import util.UserUtils;

@RestController
@RequestMapping(value = "/auth",
        produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class AuthController {

    @Autowired
    private TokenHandler tokenHandler;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private ClientDao clientDao;

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public BaseResponse adminLogin(@RequestBody LoginForm loginForm) {
        if (UserUtils.defineUserType(loginForm.email) == UserType.CLIENT) {
            return new ErrorResponse(ErrorType.ACCESS_DENIED);
        }

        try {
            Employee employee = employeeDao.findOne(loginForm.email);
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
                    ? clientDao.findOne(loginForm.email)
                    : employeeDao.findOne(loginForm.email);

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
