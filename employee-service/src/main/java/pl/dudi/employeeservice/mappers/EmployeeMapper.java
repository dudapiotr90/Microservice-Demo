package pl.dudi.employeeservice.mappers;

import org.mapstruct.Mapper;
import pl.dudi.employeeservice.dto.EmployeeDto;
import pl.dudi.employeeservice.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee mapToEmployee(EmployeeDto employeeDto);
    EmployeeDto mapToEmployeeDto(Employee employee);
}
