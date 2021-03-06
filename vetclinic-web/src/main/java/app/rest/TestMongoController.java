package app.rest;

import entities.issue.Issue;
import entities.issue.IssueInfo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.RequestService;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController()
@CrossOrigin
@RequestMapping("/mongo")
public class TestMongoController {

    @Autowired
    private RequestService requestService;


    @RequestMapping("/test")
    public String test() {
        return "hello";
    }

    @RequestMapping("/all")
    public List<Issue> findAll() {
        return null;
    }

    @RequestMapping("/{id}")
    public Issue findById(@PathVariable("id") String id) {
        ObjectId objectId = new ObjectId(id);
        return requestService.findById(objectId);
    }

    @RequestMapping("/date")
    public String date() {
        java.util.Date currDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(currDate.getTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        return dateFormat.format(new java.util.Date());
    }

    @RequestMapping("/last")
    public IssueInfo findLastClientRequest() {
        IssueInfo lastClientRequest = requestService.findLastClientRequest("denis@mail.ru");
        return lastClientRequest;
    }

}
