package pl.dudi.employeeservice.service.impl;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.dudi.employeeservice.dto.APIResponseDto;
import pl.dudi.employeeservice.dto.DepartmentDto;
import pl.dudi.employeeservice.dto.EmployeeDto;
import pl.dudi.employeeservice.dto.OrganizationDto;
import pl.dudi.employeeservice.entity.Employee;
import pl.dudi.employeeservice.exception.EmailAlreadyExistException;
import pl.dudi.employeeservice.exception.EmployeeNotFoundException;
import pl.dudi.employeeservice.mappers.EmployeeMapper;
import pl.dudi.employeeservice.repository.EmployeeRepository;
import pl.dudi.employeeservice.service.DepartmentAPIClient;
import pl.dudi.employeeservice.service.EmployeeService;
import pl.dudi.employeeservice.service.OrganizationAPIClient;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    private final DepartmentAPIClient departmentApiClient;
    private final OrganizationAPIClient organizationAPIClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if (optionalEmployee.isPresent()) {
            throw new EmailAlreadyExistException(String.format("Email: [%s] is taken", employeeDto.getEmail()));
        }
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    //    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        log.info("inside getEmployeeById() method");

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new EmployeeNotFoundException(String.format("Employee with id:[%s] doesn't exist!", employeeId))
        );
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        DepartmentDto departmentDto = departmentApiClient.getDepartment(employee.getDepartmentCode());

        OrganizationDto organizationDto = organizationAPIClient.getOrganization(employee.getOrganizationCode());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
        log.info("inside getDefaultDepartment() method");
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new EmployeeNotFoundException(String.format("Employee with id:[%s] doesn't exist!", employeeId))
        );
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

}
