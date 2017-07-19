package app.rest;

import app.responses.*;
import app.util.RoleManager;
import dto.AnimalDto;
import dto.EmployeeDto;
import dto.PatientDto;
import enums.PatientStatus;
import enums.Role;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.AnimalService;
import services.PatientService;
import util.DateManager;

import java.sql.Date;


@RestController
@RequestMapping(value = "/api/patient", produces = "application/json")
@CrossOrigin
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private AnimalService animalService;

    @RequestMapping(method = RequestMethod.GET)
    public BaseResponse getAll() {
        return new DataResponse<>(patientService.findAll());
    }

    @RequestMapping(value = "/progress", method = RequestMethod.GET)
    public BaseResponse getInProgressPatient() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (RoleManager.hasRole(authentication, Role.CLIENT)) {
            return new ErrorResponse(ErrorType.ACCESS_DENIED);
        }

        return new DataResponse<>(patientService.findInProgress(authentication.getName()));
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public BaseResponse getNew() {
        return new DataResponse<>(patientService.findNew());
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public BaseResponse getInfo(@PathVariable("id") Integer id) {
        return new DataResponse<>(patientService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse add(@RequestBody PatientDto patient) {
        if (patient != null) {
            try {
                AnimalDto animal = patient.getAnimal();
                animal.setIll(true);
                animalService.update(animal);
                patientService.add(patient);

                return new SuccessResponse();
            } catch (ObjectNotFoundException e) {
                return new ErrorResponse(ErrorType.BAD_REQUEST);
            }
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

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public BaseResponse addEmployeeToPatient(@PathVariable("id") Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (RoleManager.hasRole(authentication, Role.CLIENT)) {
            return new ErrorResponse(ErrorType.ACCESS_DENIED);
        }

        PatientDto patient = patientService.findById(id);
        if (patient != null) {

            patient.setEmployee(new EmployeeDto(authentication.getName()));
            patient.setStatus(PatientStatus.IN_PROGRESS);
            java.util.Date currDate = new java.util.Date();
            patient.setStartDate(new Date(currDate.getTime()));

            try {
                patientService.update(patient);
            } catch (ObjectNotFoundException e) {
                return new ErrorResponse(ErrorType.BAD_REQUEST);
            }
        } else {
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }

        return new SuccessResponse();
    }

}
