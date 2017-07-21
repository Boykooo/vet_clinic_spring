package app.rest;

import forms.ClientRequestForm;
import app.responses.BaseResponse;
import app.responses.ErrorResponse;
import app.responses.ErrorType;
import app.responses.SuccessResponse;
import dto.ClientDto;
import enums.Role;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.ClientService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/client", produces = "application/json")
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)

    public List<ClientDto> getAll() {
        return clientService.findAll();
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public ClientDto getClientInfo(@PathVariable("email") String email) {
        return clientService.findById(email);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ClientDto getClientInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean founded = false;
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (Objects.equals(authority.getAuthority(), Role.CLIENT.toString())) {
                founded = true;
                break;
            }
        }
        if (founded) {
            return clientService.findById(authentication.getName());
        }

        return null;
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("email") String email) {
        clientService.delete(email);

        return new SuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse add(@RequestBody ClientDto user) {
        try {
            clientService.add(user);
        } catch (ObjectAlreadyExistException e) {
            return new ErrorResponse(ErrorType.OBJECT_ALREADY_EXISTS);
        }

        return new SuccessResponse();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public BaseResponse update(@RequestBody ClientDto user) {
        try {
            clientService.update(user);
        } catch (ObjectNotFoundException e) {
            return new ErrorResponse(ErrorType.USER_NOT_FOUND);
        }

        return new SuccessResponse();
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public BaseResponse addRequest(@RequestBody ClientRequestForm requestForm) {

        if (requestForm.animalId == null) {
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }

        requestForm.clientEmail =  SecurityContextHolder.getContext().getAuthentication().getName();
        clientService.addRequest(requestForm);

        return new SuccessResponse();
    }
}
