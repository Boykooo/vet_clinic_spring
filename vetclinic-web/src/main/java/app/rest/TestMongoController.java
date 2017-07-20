package app.rest;

import mongoEntities.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.RequestRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController()
@CrossOrigin
@RequestMapping("/mongo")
public class TestMongoController {

    @Autowired
    private RequestRepository requestRepository;


    @RequestMapping("/test")
    public String test(){
        return "hello";
    }

    @RequestMapping("/all")
    public List<Request> findAll(){
        return requestRepository.findAll();
    }

    @RequestMapping("/date")
    public java.sql.Date date(){
        java.util.Date currDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(currDate.getTime());

        return date;

    }

}
