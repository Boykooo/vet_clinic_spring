package services;

import dto.EmployeeDto;
import dto.PatientDto;
import entities.Employee;
import entities.Patient;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import dao.EmployeeDao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService implements GenericService<EmployeeDto, String> {

    @Autowired
    private PatientService patientService;
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<EmployeeDto> findAll() {
        List<EmployeeDto> employees = new ArrayList<>();
        employeeDao.findAll().stream().forEach(
                (Employee employee) -> employees.add(convertToDto(employee))
        );

        return employees;
    }

    @Override
    public List<EmployeeDto> getLimit(Integer startPage, Integer amount) {
        List<EmployeeDto> employees = new ArrayList<>();
        startPage--;

        Long count = employeeDao.count();
        Integer newAmount = amount;
        if ((startPage * amount + amount) > count) {
            Long lValue = count - startPage * amount;
            newAmount = lValue.intValue();
        }

        if (newAmount <= 0) {
            return employees;
        }

        Pageable limit = new PageRequest(startPage * amount, newAmount);
        employeeDao.findAll(limit).forEach(
                (Employee employee) -> employees.add(convertToDto(employee))
        );

        return employees;
    }

    @Override
    public EmployeeDto findById(String key) {
        return convertToDto(employeeDao.findOne(key));
    }

    @Override
    public void add(EmployeeDto employeeDto) throws ObjectAlreadyExistException {
        Employee employee = employeeDao.findOne(employeeDto.getEmail());
        if (employee == null) {
            employeeDao.save(convertToEntity(employeeDto));
        } else {
            throw new ObjectAlreadyExistException();
        }
    }

    @Override
    public void update(EmployeeDto employeeDto) throws ObjectNotFoundException {
        Employee employee = employeeDao.findOne(employeeDto.getEmail());
        if (employee != null) {
            employeeDto.setRegDate(employee.getRegDate());
            employeeDao.save(convertToEntity(employeeDto));
        } else {
            throw new ObjectNotFoundException();
        }

    }

    @Override
    public void delete(String key) {
        employeeDao.delete(key);
    }

    public Long count() {
        return employeeDao.count();
    }

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = convertToDtoWithoutDepend(employee);

        List<PatientDto> patients = new ArrayList<>();
        employee.getPatients().stream().forEach(
                (Patient patient) -> patients.add(patientService.convertToDto(patient))
        );
        dto.setPatients(patients);

        return dto;
    }

    public EmployeeDto convertToDtoWithoutDepend(Employee employee) {
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

    public Employee convertToEntity(EmployeeDto dto) {
        Employee employee = new Employee();

        employee.setEmail(dto.getEmail());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
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
