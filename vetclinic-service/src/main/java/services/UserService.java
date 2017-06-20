package services;

import dto.AnimalDto;
import dto.UserDto;
import entities.Animal;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.sql.Date;
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
        userRepository.save(convertToEntity(userDto));
    }

    public void update(UserDto userDto) {
        User user = userRepository.findOne(userDto.getEmail());
        userDto.setRegDate(user.getRegDate());
        userRepository.save(convertToEntity(userDto));
    }

    public void delete(String key) {
        userRepository.delete(key);
    }

    @Override
    public List<UserDto> getLimit(Integer startPage, Integer amount) {
        return null;
    }

    private UserDto convertToDto(User user){
        UserDto userDto = convertToDtoWithoutDepend(user);

        List<AnimalDto> animals = new ArrayList<>();
        user.getAnimals().stream().forEach(
                (Animal animal) -> animals.add(animalService.convertToDto(animal))
        );

        userDto.setAnimals(animals);

       return userDto;
    }

    public UserDto convertToDtoWithoutDepend(User user){
        UserDto userDto = new UserDto();
        if (user != null)
        {
            userDto.setEmail(user.getEmail());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setPassword(user.getPassword());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDto.setRegDate(user.getRegDate());
        }

        return  userDto;
    }

    public User convertToEntity(UserDto dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getRegDate() == null){
            java.util.Date currDate = new java.util.Date();
            user.setRegDate(new Date(currDate.getTime()));
        }
        else{
            user.setRegDate(dto.getRegDate());
        }

        return user;
    }
}
