package pl.dudi.departmentservice.mappers;

import org.mapstruct.Mapper;
import pl.dudi.departmentservice.dto.DepartmentDto;
import pl.dudi.departmentservice.entity.Department;

@Mapper
public interface DepartmentMapper {

    Department mapToDepartment(DepartmentDto departmentDto);
    DepartmentDto mapToDepartmentDto(Department department);
}