package services;

import java.util.List;

/**
 * Created by andrey on 07.06.17.
 */
public interface GenericService<Entity, PK> {
    List<Entity> findAll();
    Entity findById(PK key);
    void add(Entity entity);
    void update(Entity entity);
    void delete(PK key);
    List<Entity> getLimit(Integer startPage, Integer amount);
    public Long count();
}
