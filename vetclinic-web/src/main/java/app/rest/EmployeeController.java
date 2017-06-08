package app.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by andrey on 08.06.17.
 */

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @RequestMapping("/all")
    public void getAll(){

    }
}
