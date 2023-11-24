package pl.dudi.employeeservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dudi.employeeservice.dto.APIResponseDto;
import pl.dudi.employeeservice.dto.DepartmentDto;
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

    private final RestTemplate restTemplate;

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
    public APIResponseDto getEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new EmployeeNotFoundException(String.format("Employee with id:[%s] doesn't exist!", employeeId))
        );
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
            "http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
            DepartmentDto.class
        );

        DepartmentDto departmentDto = responseEntity.getBody();
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

//        return employeeMapper.mapToEmployeeDto(employee);
        return apiResponseDto;
    }
}
