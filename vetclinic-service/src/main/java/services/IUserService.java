package services;

import dto.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by andrey on 07.06.17.
 */

public interface IUserService{

    List<UserDto> findAll();
    UserDto findById(String key);
    void add(UserDto entity);
    void update(UserDto entity);
    boolean delete(String key);
}
