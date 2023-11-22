package pl.dudi.departmentservice.service;

import org.springframework.stereotype.Service;
import pl.dudi.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String departmentCode);
}
