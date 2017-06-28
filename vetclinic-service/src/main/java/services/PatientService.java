package services;

import dto.PatientDto;
import entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andrey on 08.06.17.
 */

@Service
public class PatientService implements GenericService<PatientDto, Integer> {


    @Autowired
    private AnimalService animalService;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<PatientDto> findAll() {
        return null;
    }

    @Override
    public PatientDto findById(Integer key) {
        return null;
    }

    @Override
    public void add(PatientDto patientDto) {

    }

    @Override
    public void update(PatientDto patientDto) {

    }

    @Override
    public void delete(Integer key) {
    }

    @Override
    public List<PatientDto> getLimit(Integer startPage, Integer amount) {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    public PatientDto convertToDto(Patient patient) {
        PatientDto dto = new PatientDto();
        if (patient != null)
        {
            dto.setId(patient.getId());
            dto.setDescription(patient.getDescription());
            dto.setStartDate(patient.getStartDate());
            dto.setEndDate(patient.getEndDate());
            dto.setStatus(patient.getStatus());
            dto.setAnimal(animalService.convertToDto(patient.getAnimal()));
            dto.setEmployee(employeeService.convertToDtoWithoutDepend(patient.getEmployee()));
        }

        return dto;
    }
}
