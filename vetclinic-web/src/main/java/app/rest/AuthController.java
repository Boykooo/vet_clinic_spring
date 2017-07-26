package app.rest;


import app.responses.*;
import dao.ClientDao;
import dao.EmployeeDao;
import entities.BaseUser;
import entities.Client;
import entities.Employee;
import enums.Role;
import enums.UserType;
import forms.LoginForm;
import forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
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
        try {
            if (UserUtils.defineUserType(loginForm.email) == UserType.CLIENT) {
                return new ErrorResponse(ErrorType.ACCESS_DENIED);
            }
        } catch (UsernameNotFoundException e) {
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
            UserType userType = UserUtils.defineUserType(loginForm.email);

            BaseUser user = (userType == UserType.CLIENT)
                    ? clientDao.findOne(loginForm.email)
                    : employeeDao.findOne(loginForm.email);

            if (user == null) {
                return new ErrorResponse(ErrorType.BAD_REQUEST);
            }

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

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public BaseResponse registration(@RequestBody RegistrationForm registrationForm){
        try {
            UserType userType = UserUtils.defineUserType(registrationForm.email);

            if (userType == UserType.CLIENT) {
                clientDao.save(new Client(
                        registrationForm.email,
                        new BCryptPasswordEncoder().encode(registrationForm.password),
                        registrationForm.phoneNumber,
                        registrationForm.firstName,
                        registrationForm.lastName,
                        DateManager.getCurrentSqlDate()
                ));

            } else {
                employeeDao.save(new Employee(
                        registrationForm.email,
                        new BCryptPasswordEncoder().encode(registrationForm.password),
                        registrationForm.phoneNumber,
                        registrationForm.firstName,
                        registrationForm.lastName,
                        Role.EMPLOYEE,
                        DateManager.getCurrentSqlDate()
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }

        return new SuccessResponse();
    }
}
