package app.rest;

import app.entities.AnimalForm;
import app.responses.BaseResponse;
import app.responses.ErrorResponse;
import app.responses.ErrorType;
import app.responses.SuccessResponse;
import dto.AnimalDto;
import exceptions.ObjectAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.AnimalMongoService;
import services.AnimalService;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/animal", produces = "application/json")
public class AnimalController {

    @Autowired
    private AnimalMongoService animalMongoService;
    @Autowired
    private AnimalService animalService;

    @RequestMapping(value = "/{id}/image", method = RequestMethod.POST)
    public BaseResponse uploadImage(@PathVariable("id") Integer id,
                                    @RequestBody MultipartFile file) {
        try {
            animalMongoService.add(file.getInputStream(), id);
        } catch (ObjectAlreadyExistException | IOException e) {
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }

        return new SuccessResponse();
    }

    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse add(@RequestBody AnimalForm form) throws IOException {
        if (form != null) {
            AnimalDto dto = animalService.add(new AnimalDto(form.name, form.age, form.description));
            if (form.file != null){
                try {
                    animalMongoService.add(form.file.getInputStream(), dto.getId());
                } catch (ObjectAlreadyExistException e) {
                    return new ErrorResponse(ErrorType.OBJECT_ALREADY_EXISTS);
                }
            }
        } else {
            return new ErrorResponse(ErrorType.BAD_REQUEST);
        }

        return new SuccessResponse();
    }

}
