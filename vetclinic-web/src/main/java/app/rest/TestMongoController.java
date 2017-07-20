package app.rest;

import mongoEntities.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.RequestRepository;
import services.RequestService;

import java.util.List;

@RestController()
@CrossOrigin
@RequestMapping("/mongo")
public class TestMongoController {

    @Autowired
    private RequestService requestService;


    @RequestMapping("/test")
    public String test(){
        return "hello";
    }

    @RequestMapping("/all")
    public List<Request> findAll(){
        return null;
    }

    @RequestMapping("/date")
    public java.sql.Date date(){
        java.util.Date currDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(currDate.getTime());

        return date;

    }

    @RequestMapping("/last")
    public Request findLastClientRequest(){
        return requestService.findLastClientRequest("client email");
    }

}
