package pl.dudi.employeeservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.dudi.employeeservice.dto.EmployeeDto;
import pl.dudi.employeeservice.entity.Employee;
import pl.dudi.employeeservice.exception.EmailAlreadyExistException;
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
    private final ModelMapper modelMapper;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if (optionalEmployee.isPresent()) {
            throw new EmailAlreadyExistException(String.format("Email: [%s] is taken", employeeDto.getEmail()));
        }
//        Employee employee = employeeMapper.mapToEmployee(employeeDto);
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);

//        return employeeMapper.mapToEmployeeDto(savedEmployee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }
    @Override
    public EmployeeDto getEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new EmployeeNotFoundException(String.format("Employee with id:[%s] doesn't exist!",employeeId))
        );
//        return employeeMapper.mapToEmployeeDto(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }
}
