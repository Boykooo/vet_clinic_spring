package app.rest;

import dao.EsPatientDao;
import entities.EsPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/es", produces = "application/json")
@CrossOrigin
public class EsController {

    @Autowired
    private EsPatientDao esPatientDao;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<EsPatient> getAll() {
        List<EsPatient> patientList = new ArrayList<>();

        Iterable<EsPatient> all = esPatientDao.findAll();
        all.forEach(
                patientList::add
        );

        return patientList;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void add() {
        EsPatient esPatient = new EsPatient("test1", "test2");
        esPatientDao.save(esPatient);
    }
}
