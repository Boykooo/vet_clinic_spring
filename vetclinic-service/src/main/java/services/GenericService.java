package services;

import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;

import java.util.List;


public interface GenericService<Entity, PK> {
    List<Entity> findAll();
    Entity findById(PK key);
    void add(Entity entity) throws ObjectAlreadyExistException;
    void update(Entity entity) throws ObjectNotFoundException;
    void delete(PK key);
    List<Entity> getLimit(String email, Integer startPage, Integer amount);
    Long count();
}
