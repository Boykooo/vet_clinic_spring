package services;

import dao.PatientDao;
import dto.PatientDto;
import entities.Employee;
import entities.Patient;
import enums.PatientStatus;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService implements GenericService<PatientDto, Integer> {


    @Autowired
    private AnimalService animalService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PatientDao patientDao;

    @Override
    public List<PatientDto> findAll() {
        List<PatientDto> patients = new ArrayList();
        patientDao.findAll()
                .forEach(
                        (Patient patient) -> patients.add(convertToDto(patient))
                );

        return patients;
    }

    public List<PatientDto> findNew() {
        List<PatientDto> newPatients = new ArrayList<>();

        patientDao.findNew().forEach(
                (patient -> newPatients.add(convertToDto(patient)))
        );

        return newPatients;
    }

    public List<PatientDto> findInProgress(String email) {
        List<PatientDto> newPatients = new ArrayList<>();

        patientDao.findInProgress(email).forEach(
                (patient -> newPatients.add(convertToDto(patient)))
        );

        return newPatients;
    }

    @Override
    public PatientDto findById(Integer key) {
        return convertToDto(patientDao.findOne(key));
    }

    @Override
    public void add(PatientDto patientDto) {
        patientDao.save(convertToEntity(patientDto));
    }

    @Override
    public void update(PatientDto patientDto) throws ObjectNotFoundException {
        Patient patient = patientDao.findOne(patientDto.getId());
        if (patient != null) {
            Patient updatePatient = convertToEntity(patientDto);

            if (updatePatient.getStatus() == PatientStatus.DONE ||
                    updatePatient.getStatus() == PatientStatus.REJECTED) {

                java.util.Date currDate = new java.util.Date();
                updatePatient.setEndDate(new Date(currDate.getTime()));
            }

            patientDao.save(updatePatient);
        } else {
            throw new ObjectNotFoundException();
        }
    }

    @Override
    public void delete(Integer key) {
        patientDao.delete(key);
    }

    @Override
    public List<PatientDto> getLimit(String email, Integer startPage, Integer amount) {

        List<PatientDto> patients = new ArrayList();
        patientDao.findInProgressPagable(email, new PageRequest(startPage, amount)).getContent()
                .forEach(
                        (Patient patient) -> patients.add(convertToDto(patient))
                );

        return patients;
    }

    @Override
    public Long count() {
        return patientDao.count();
    }

    public Long getCountByEmail(String email) {
        return patientDao.getCoundByEmployeeEmail(email);
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

        if (dto != null) {
            patient.setDescription(dto.getDescription());
            patient.setEndDate(dto.getEndDate());
            if (dto.getStatus() == null) {
                patient.setStatus(PatientStatus.NEW);
            } else {
                patient.setStatus(dto.getStatus());
            }

            patient.setAnimal(animalService.convertToEntity(dto.getAnimal()));

            patient.setStartDate(dto.getStartDate());

            if (dto.getId() != null) {
                patient.setId(dto.getId());
            }
        }

        if (dto.getEmployee() != null) {
            patient.setEmployee(new Employee(dto.getEmployee().getEmail()));
        }


        return patient;
    }

}
