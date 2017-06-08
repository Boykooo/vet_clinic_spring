package services;

import dto.EmployeeDto;
import dto.PatientDto;
import entities.Employee;
import entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 08.06.17.
 */

@Service
public class EmployeeService implements GenericService<EmployeeDto, String> {

    @Autowired
    private PatientService patientService;

    @Override
    public List<EmployeeDto> findAll() {
        return null;
    }

    @Override
    public EmployeeDto findById(String key) {
        return null;
    }

    @Override
    public void add(EmployeeDto employeeDto) {

    }

    @Override
    public void update(EmployeeDto employeeDto) {

    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    private EmployeeDto convertToDto(Employee employee){
        EmployeeDto dto = convertToDtoWithoutList(employee);

        List<PatientDto> patients = new ArrayList<>();
        employee.getPatients().stream().forEach(
                (Patient patient) -> patients.add(patientService.convertToDto(patient))
        );
        dto.setPatients(patients);

        return dto;
    }

    public EmployeeDto convertToDtoWithoutList(Employee employee){
        EmployeeDto dto = new EmployeeDto();
        if (employee != null)
        {
            dto.setEmail(employee.getEmail());
            dto.setFirstName(employee.getFirstName());
            dto.setLastName(employee.getLastName());
            dto.setPassword(employee.getPassword());
            dto.setPhoneNumber(employee.getPhoneNumber());
            dto.setRole(employee.getRole());
        }

        return dto;
    }
}
