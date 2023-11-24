package pl.dudi.employeeservice.service;

import pl.dudi.employeeservice.dto.APIResponseDto;
import pl.dudi.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployee(Long employeeId);
}
