package services;

import dto.AnimalDto;
import entities.Animal;
import entities.Client;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.AnimalDao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalDao animalDao;
    @Autowired
    private ClientService clientService;
    @Autowired
    private PatientService patientService;

    public List<AnimalDto> findAll() {
        List<AnimalDto> animals = new ArrayList<>();
        animalDao.findAll().stream().forEach(
                (Animal animal) -> animals.add(convertToDto(animal))
        );

        return animals;
    }

    public AnimalDto findById(Integer key) {
        return convertToDto(animalDao.findOne(key));
    }

    public AnimalDto add(AnimalDto animalDto) {
        Animal animal = animalDao.save(convertToEntity(animalDto));

        return convertToDto(animal);
    }

    public void update(AnimalDto animalDto) throws ObjectNotFoundException {
        Animal animal = animalDao.findOne(animalDto.getId());
        if (animal != null) {
            animalDao.save(convertToEntity(animalDto));
        } else {
            throw new ObjectNotFoundException();
        }
    }

    public void delete(Integer key) {
        animalDao.delete(key);
    }

    public List<AnimalDto> getLimit(Integer startPage, Integer amount) {
        return null;
    }

    public Long count() {
        return null;
    }

    public AnimalDto convertToDto(Animal animal) {
        AnimalDto dto = null;
        if (animal != null) {
            dto = convertToDtoWithoutDepend(animal);
            dto.setClient(clientService.convertToDtoWithoutDepend(animal.getClient()));
            dto.setPatient(patientService.convertToDto(animal.getPatient()));
        }

        return dto;
    }

    public AnimalDto convertToDtoWithoutDepend(Animal animal) {
        AnimalDto dto = new AnimalDto();
        if (animal != null) {
            dto.setAge(animal.getAge());
            dto.setDescription(animal.getDescription());
            dto.setId(animal.getId());
            dto.setName(animal.getName());
            dto.setRegDate(animal.getRegDate());
            dto.setIll(animal.isIll());
        }

        return dto;
    }

    public Animal convertToEntity(AnimalDto dto) {
        Animal animal = new Animal();
        if (dto.getId() != null) {
            animal.setId(dto.getId());
        }
        animal.setAge(dto.getAge());
        animal.setDescription(dto.getDescription());
        animal.setName(dto.getName());
        animal.setIll(dto.isIll());

        if (dto.getRegDate() == null) {
            java.util.Date currDate = new java.util.Date();
            animal.setRegDate(new Date(currDate.getTime()));
        } else {
            animal.setRegDate(dto.getRegDate());
        }

        if (dto.getClient() != null){
            animal.setClient(new Client(dto.getClient().getEmail()));
        }

        return animal;
    }

}
