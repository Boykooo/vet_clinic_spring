package app.rest;

import app.entities.AnimalForm;
import app.responses.*;
import dto.AnimalDto;
import dto.ClientDto;
import exceptions.ObjectAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.AnimalMongoService;
import services.AnimalService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/animal", produces = "application/json")
public class AnimalController {

    @Autowired
    private AnimalMongoService animalMongoService;
    @Autowired
    private AnimalService animalService;

    @RequestMapping(method = RequestMethod.GET)
    public List<AnimalDto> getAll() {
        return animalService.findAll();
    }

    @RequestMapping(value = "/{id}/image", method = RequestMethod.POST)
    public BaseResponse uploadImage(@PathVariable("id") Integer id,
                                    @RequestBody MultipartFile file) {
        try {
            animalMongoService.add(file.getInputStream(), id);
            return new SuccessResponse();

        } catch (ObjectAlreadyExistException | IOException e) {
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse add(@RequestBody AnimalForm form) throws IOException {

        if (form != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            AnimalDto dto = animalService.add(
                    new AnimalDto(
                            form.name,
                            form.age,
                            form.description,
                            new ClientDto(authentication.getName())
                    )
            );

            return new DataResponse<Integer>(dto.getId());

        } else {
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public BaseResponse getinfo(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }

        return new DataResponse<AnimalDto>(animalService.findById(id));
    }

}
