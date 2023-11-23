package pl.dudi.departmentservice.mappers;

import org.mapstruct.Mapper;
import pl.dudi.departmentservice.dto.DepartmentDto;
import pl.dudi.departmentservice.entity.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDto mapToDepartmentDto(Department department);
    Department mapToDepartment(DepartmentDto departmentDto);
}
