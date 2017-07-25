package app.rest;

import app.responses.BaseResponse;
import app.responses.DataResponse;
import app.responses.ErrorResponse;
import app.responses.ErrorType;
import app.util.RoleManager;
import dao.EsPatientDao;
import entities.EsPatient;
import enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.EsService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/es", produces = "application/json")
@CrossOrigin
public class EsController {

    @Autowired
    private EsPatientDao esPatientDao;

    @Autowired
    private EsService esService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add() {
        EsPatient esPatient1 = new EsPatient("employee@vetclinic.ru", "1@1", "Валера", 7);
        esPatientDao.save(esPatient1);
    }

    @RequestMapping(value = "/search/client/{name}", method = RequestMethod.GET)
    public BaseResponse search(@PathVariable("name") String name) {
        return new DataResponse<>(esService.searchByClientName(name));
    }

    @RequestMapping(method = RequestMethod.GET)
    public BaseResponse getAllByEmployeeEmail() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (RoleManager.hasRole(authentication, Role.CLIENT)) {
            return new ErrorResponse(ErrorType.ACCESS_DENIED);
        }

        return new DataResponse<>(esService.searchByEmployeeEmail(authentication.getName()));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void deleteAll() {

        esPatientDao.deleteAll();

    }

}
