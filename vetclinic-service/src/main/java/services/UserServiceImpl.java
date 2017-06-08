package services;

import dto.PatientDto;
import dto.UserDto;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 07.06.17.
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> findAll() {
        List<UserDto> users = new ArrayList<UserDto>();
        userRepository.findAll().stream().forEach(
                (User user) -> users.add(convertToDto(user))
        );

        return users;
    }

    public UserDto findById(String key) {
        return null;
    }

    public void add(UserDto userDto) {

    }

    public void update(UserDto userDto) {

    }

    public boolean delete(String key) {
        return false;
    }

    private UserDto convertToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole());

       /* List<PatientDto> patients = new ArrayList<PatientDto>();
        user...*/

       return userDto;
    }

}
