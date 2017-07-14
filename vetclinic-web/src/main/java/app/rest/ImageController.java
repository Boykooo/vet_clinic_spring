package app.rest;


import exceptions.ObjectAlreadyExistException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.AnimalMongoService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/images", produces = "application/json")
@CrossOrigin
public class ImageController {

    @Autowired
    private ServletContext servletContext;
    @Autowired
    private AnimalMongoService animalMongoService;

    @RequestMapping(value = "/animal/{id}", method = RequestMethod.GET)
    public void getAnimalImage(@PathVariable("id") Integer id,
                               HttpServletResponse response) throws IOException {
        InputStream inputStream = animalMongoService.findById(id);
        if (inputStream != null) {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            IOUtils.copy(inputStream, response.getOutputStream());
        }
    }
}
