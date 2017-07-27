package services;

import dto.PatientDto;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;

import java.util.List;


public interface GenericService<Entity, PK> {
    List<Entity> findAll();
    Entity findById(PK key);
    PatientDto add(Entity entity) throws ObjectAlreadyExistException;
    PatientDto update(Entity entity) throws ObjectNotFoundException;
    void delete(PK key);
    List<Entity> getPage(String email, Integer startPage, Integer amount);
    Long count();
}
