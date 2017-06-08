package services;

import java.util.List;

/**
 * Created by andrey on 07.06.17.
 */
public interface GenericSevice<Entity, PK> {
    List<Entity> findAll();
    Entity findById(PK key);
    void add(Entity entity);
    void update(Entity entity);
    boolean delete(PK key);
}