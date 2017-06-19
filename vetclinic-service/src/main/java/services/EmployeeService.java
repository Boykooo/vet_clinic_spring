package services;

import dto.EmployeeDto;
import dto.PatientDto;
import entities.Employee;
import entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EmployeeRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 08.06.17.
 */

@Service
public class EmployeeService implements GenericService<EmployeeDto, String> {

    @Autowired
    private PatientService patientService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> findAll() {
        List<EmployeeDto> employees = new ArrayList<>();
        employeeRepository.findAll().stream().forEach(
                (Employee employee) -> employees.add(convertToDto(employee))
        );

        return employees;
    }

    @Override
    public EmployeeDto findById(String key) {
        return convertToDto(employeeRepository.findOne(key));
    }

    @Override
    public void add(EmployeeDto employeeDto) {
        employeeRepository.save(convertToEntity(employeeDto));
    }

    @Override
    public void update(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findOne(employeeDto.getEmail());
        employeeDto.setRegDate(employee.getRegDate());

        employeeRepository.save(convertToEntity(employeeDto));
    }

    @Override
    public void delete(String key) {
        employeeRepository.delete(key);
    }

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = convertToDtoWithoutList(employee);

        List<PatientDto> patients = new ArrayList<>();
        employee.getPatients().stream().forEach(
                (Patient patient) -> patients.add(patientService.convertToDto(patient))
        );
        dto.setPatients(patients);

        return dto;
    }

    public EmployeeDto convertToDtoWithoutList(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        if (employee != null) {
            dto.setEmail(employee.getEmail());
            dto.setFirstName(employee.getFirstName());
            dto.setLastName(employee.getLastName());
            dto.setPassword(employee.getPassword());
            dto.setPhoneNumber(employee.getPhoneNumber());
            dto.setRole(employee.getRole());
            dto.setRegDate(employee.getRegDate());
        }

        return dto;
    }

    private Employee convertToEntity(EmployeeDto dto) {
        Employee employee = new Employee();

        employee.setEmail(dto.getEmail());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setPassword(dto.getPassword());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setRole(dto.getRole());

        if (dto.getRegDate() == null) {
            java.util.Date currDate = new java.util.Date();
            employee.setRegDate(new Date(currDate.getTime()));

        } else {
            employee.setRegDate(dto.getRegDate());
        }

        return employee;
    }
}
