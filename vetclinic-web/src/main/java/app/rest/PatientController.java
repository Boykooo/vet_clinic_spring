package app.rest;

import app.responses.*;
import app.util.RoleManager;
import dto.PatientDto;
import enums.Role;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.PatientService;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @RequestMapping(method = RequestMethod.GET)
    public BaseResponse getAll() {
        return new DataResponse<>(patientService.findAll());
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public BaseResponse getInfo(@PathVariable("id") Integer id) {
        return new DataResponse<>(patientService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse add(@RequestBody PatientDto patient) {


        if (patient != null) {
            patientService.add(patient);
            return new SuccessResponse();
        } else {
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public BaseResponse update(@RequestBody PatientDto patient) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (RoleManager.hasRole(authentication, Role.CLIENT)) {
            return new ErrorResponse(ErrorType.ACCESS_DENIED);
        }

        if (patient != null) {
            try {
                patientService.update(patient);
                return new SuccessResponse();
            } catch (ObjectNotFoundException e) {
                return new ErrorResponse(ErrorType.OBJECT_NOT_FOUND);
            }
        } else {
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id") Integer id) {
        patientService.delete(id);
        return new SuccessResponse();
    }
}
