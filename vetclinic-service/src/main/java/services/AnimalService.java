package services;

import dto.AnimalDto;
import entities.Animal;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AnimalRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService implements GenericService<AnimalDto,Integer> {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ClientService clientService;
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
    public void add(AnimalDto animalDto) throws ObjectAlreadyExistException {
        Animal animal = animalRepository.findOne(animalDto.getId());
        if (animal == null){
            animalRepository.save(convertToEntity(animalDto));
        } else {
            throw new ObjectAlreadyExistException();
        }
    }

    @Override
    public void update(AnimalDto animalDto) throws ObjectNotFoundException {
        Animal animal = animalRepository.findOne(animalDto.getId());
        if (animal != null){
            animalRepository.save(convertToEntity(animalDto));
        } else {
            throw new ObjectNotFoundException();
        }
    }

    @Override
    public void delete(Integer key) {
        animalRepository.delete(key);
    }

    @Override
    public List<AnimalDto> getLimit(Integer startPage, Integer amount) {
        return null;
    }

    @Override
    public Long count() {
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

            dto.setUser(clientService.convertToDtoWithoutDepend(animal.getClient()));
            dto.setPatient(patientService.convertToDto(animal.getPatient()));
        }

        return dto;
    }

    public Animal convertToEntity(AnimalDto dto){
        Animal animal = new Animal();
        if (dto.getId() != null){
            animal.setId(dto.getId());
        }
        animal.setAge(dto.getAge());
        animal.setDescription(dto.getDescription());
        animal.setName(dto.getName());
        if (dto.getRegDate() == null) {
            java.util.Date currDate = new java.util.Date();
            animal.setRegDate(new Date(currDate.getTime()));
        } else {
            animal.setRegDate(dto.getRegDate());
        }

        return animal;
    }

}
