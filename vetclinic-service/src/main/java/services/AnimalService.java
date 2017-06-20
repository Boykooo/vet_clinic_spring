package services;

import dto.AnimalDto;
import entities.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AnimalRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 08.06.17.
 */

@Service
public class AnimalService implements GenericService<AnimalDto,Integer> {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PatientService patientService;

    @Override
    public List<AnimalDto> findAll() {
        List<AnimalDto> animals = new ArrayList<>();
        animalRepository.findAll().stream().forEach(
                (Animal animal) -> animals.add(convertToDto(animal))
        );

        return animals;
    }

    @Override
    public AnimalDto findById(Integer key) {
        return convertToDto(animalRepository.findOne(key));
    }

    @Override
    public void add(AnimalDto animalDto) {

    }

    @Override
    public void update(AnimalDto animalDto) {

    }

    @Override
    public void delete(Integer key) {
    }

    @Override
    public List<AnimalDto> getLimit(Integer startPage, Integer amount) {
        return null;
    }

    public AnimalDto convertToDto(Animal animal){
        AnimalDto dto = new AnimalDto();
        if (animal != null)
        {
            dto.setAge(animal.getAge());
            dto.setDescription(animal.getDescription());
            dto.setId(animal.getId());
            dto.setName(animal.getName());
            dto.setRegDate(animal.getRegDate());

            dto.setUser(userService.convertToDtoWithoutDepend(animal.getUser()));
            dto.setPatient(patientService.convertToDto(animal.getPatient()));
        }

        return dto;
    }

}
