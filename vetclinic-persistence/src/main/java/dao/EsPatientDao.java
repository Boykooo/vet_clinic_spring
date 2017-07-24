package dao;

import entities.EsPatient;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsPatientDao extends ElasticsearchRepository<EsPatient, String> {

}
