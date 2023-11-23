package pl.dudi.employeeservice.service;

import pl.dudi.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployee(Long employeeId);
}
