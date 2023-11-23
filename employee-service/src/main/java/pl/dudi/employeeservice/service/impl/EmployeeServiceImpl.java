package pl.dudi.employeeservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dudi.employeeservice.dto.EmployeeDto;
import pl.dudi.employeeservice.entity.Employee;
import pl.dudi.employeeservice.exception.EmployeeNotFoundException;
import pl.dudi.employeeservice.mappers.EmployeeMapper;
import pl.dudi.employeeservice.repository.EmployeeRepository;
import pl.dudi.employeeservice.service.EmployeeService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
//        Employee employee = new Employee(
//            employeeDto.getId(),
//            employeeDto.getFirstName(),
//            employeeDto.getLastName(),
//            employeeDto.getEmail()
//        );
        Employee employee = employeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.mapToEmployeeDto(savedEmployee);
    }
    @Override
    public EmployeeDto getEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new EmployeeNotFoundException(String.format("Employee with id:[%s] doesn't exist!",employeeId))
        );
        return new EmployeeDto(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getEmail()
        );
    }
}
