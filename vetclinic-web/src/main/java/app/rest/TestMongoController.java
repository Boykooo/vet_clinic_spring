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
@RequestMapping(value = "/mongo", produces = "application/json")
@CrossOrigin
public class TestMongoController {

    @Autowired
    private ServletContext servletContext;
    @Autowired
    private AnimalMongoService animalMongoService;

    @RequestMapping(method = RequestMethod.GET)
    public void test(/*@PathVariable("id") Integer id,*/
                     HttpServletResponse response) throws IOException, ObjectAlreadyExistException {

        InputStream inputStream = animalMongoService.findById(11);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        IOUtils.copy(inputStream, response.getOutputStream());

//        animalMongoService.findById(10);

//        InputStream inputStream = servletContext
//                .getClassLoader()
//                .getResourceAsStream("images/stacionar.png");
//
//        animalMongoService.add(inputStream, 11);
//
//        GridFSDBFile out = gridFS.findOne(new BasicDBObject("meta", 5));
//        System.out.println(out.get("meta"));  // this will print 5
//
//        inputStream = out.getInputStream();
//        response.setContentType(MediaType.IMAGE_PNG_VALUE);
//        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getAnimalImage(@PathVariable("id") Integer id,
                               HttpServletResponse response) throws IOException {
        InputStream inputStream = animalMongoService.findById(11);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void setAnimalImage(@PathVariable("id") Integer id,
                               MultipartFile file) throws ObjectAlreadyExistException, IOException {
        animalMongoService.add(file.getInputStream(), id);
    }
}
