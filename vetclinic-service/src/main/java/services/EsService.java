package services;

import dao.EsPatientDao;
import entities.EsPatient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EsService {

    @Autowired
    private EsPatientDao esPatientDao;

    public List<EsPatient> searchByClientName(String name){
        List<EsPatient> patientList = new ArrayList<>();

        QueryBuilder qb = QueryBuilders.matchPhrasePrefixQuery(
                "clientName",
                name
        );

        esPatientDao.search(qb)
                .forEach(
                        patientList::add
                );

        return patientList;
    }

    public List<EsPatient> searchByClientEmail(String email){
        List<EsPatient> patientList = new ArrayList<>();

        QueryBuilder qb = QueryBuilders.prefixQuery(
                "clientEmail",
                email
        );

        esPatientDao.search(qb)
                .forEach(
                        patientList::add
                );

        return patientList;
    }

    public List<EsPatient> searchByEmployeeEmail(String email) {
        List<EsPatient> patientList = new ArrayList<>();

        QueryBuilder qb = QueryBuilders.matchQuery(
                "employeeEmail",
                email
        );

        esPatientDao.search(qb)
                .forEach(
                        patientList::add
                );

        return patientList;
    }

    public void add(EsPatient esPatient) {
        esPatientDao.save(esPatient);
    }

}
