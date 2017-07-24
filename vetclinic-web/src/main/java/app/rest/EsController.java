package app.rest;

import dao.EsPatientDao;
import entities.EsPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.EsService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/es", produces = "application/json")
@CrossOrigin
public class EsController {

    @Autowired
    private EsPatientDao esPatientDao;

    @Autowired
    private EsService esService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<EsPatient> getAll() {
        List<EsPatient> patientList = new ArrayList<>();

        Iterable<EsPatient> all = esPatientDao.findAll();
        all.forEach(
                patientList::add
        );

        return patientList;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add() {
        EsPatient esPatient = new EsPatient("employee@vetclinic.ru", "denis@mail.ru", "Денис Валинуров");
        esPatientDao.save(esPatient);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public List<EsPatient> search(@PathVariable("name") String name) {
        return esService.searchByClientName(name);
    }
}
