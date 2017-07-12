package services;

import dto.PatientDto;
import entities.Patient;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PatientRepository;

import java.util.ArrayList;
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
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<PatientDto> findAll() {
        List<PatientDto> patients = new ArrayList();
        patientRepository.findAll()
                .forEach(
                        (Patient patient) -> patients.add(convertToDto(patient))
                );

        return patients;
    }

    @Override
    public PatientDto findById(Integer key) {
        return convertToDto(patientRepository.findOne(key));
    }

    @Override
    public void add(PatientDto patientDto) {
        patientRepository.save(convertToEntity(patientDto));
    }

    @Override
    public void update(PatientDto patientDto) throws ObjectNotFoundException {
        Patient patient = patientRepository.findOne(patientDto.getId());
        if (patient != null) {
            patientRepository.save(convertToEntity(patientDto));
        } else {
            throw new ObjectNotFoundException();
        }
    }

    @Override
    public void delete(Integer key) {
        patientRepository.delete(key);
    }

    @Override
    public List<PatientDto> getLimit(Integer startPage, Integer amount) {
        return null;
    }

    @Override
    public Long count() {
        return patientRepository.count();
    }

    public PatientDto convertToDto(Patient patient) {
        PatientDto dto = new PatientDto();
        if (patient != null) {
            dto.setId(patient.getId());
            dto.setDescription(patient.getDescription());
            dto.setStartDate(patient.getStartDate());
            dto.setEndDate(patient.getEndDate());
            dto.setStatus(patient.getStatus());
            dto.setAnimal(animalService.convertToDtoWithoutDepend(patient.getAnimal()));
            dto.setEmployee(employeeService.convertToDtoWithoutDepend(patient.getEmployee()));
        }

        return dto;
    }

    private Patient convertToEntity(PatientDto dto) {
        Patient patient = new Patient();
        patient.setDescription(dto.getDescription());
        patient.setEndDate(dto.getEndDate());
        patient.setStatus(dto.getStatus());
        patient.setStartDate(dto.getStartDate());

        if (dto.getId() != null) {
            patient.setId(dto.getId());
        }

        return patient;
    }

}
