package services;

import dto.AnimalDto;
import dto.UserDto;
import entities.Animal;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 07.06.17.
 */

@Service
public class UserService implements GenericService<UserDto, String> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnimalService animalService;

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
        UserDto userDto = convertToDtoWithoutLists(user);

        List<AnimalDto> animals = new ArrayList<>();
        user.getAnimals().stream().forEach(
                (Animal animal) -> animals.add(animalService.convertToDto(animal))
        );

        userDto.setAnimals(animals);

       return userDto;
    }

    public UserDto convertToDtoWithoutLists(User user){
        UserDto userDto = new UserDto();
        if (user != null)
        {
            userDto.setEmail(user.getEmail());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setPassword(user.getPassword());
            userDto.setPhoneNumber(user.getPhoneNumber());
        }

        return  userDto;
    }
}
