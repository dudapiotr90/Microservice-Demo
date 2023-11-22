package pl.dudi.departmentservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dudi.departmentservice.dto.DepartmentDto;
import pl.dudi.departmentservice.entity.Department;
import pl.dudi.departmentservice.repository.DepartmentRepository;
import pl.dudi.departmentservice.service.DepartmentService;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        // convert department dto to dep JpaEntity
        Department department = new Department(
            departmentDto.getId(),
            departmentDto.getDepartmentName(),
            departmentDto.getDepartmentDescription(),
            departmentDto.getDepartmentCode()
        );

        Department savedDepartment = departmentRepository.save(department);
        return new DepartmentDto(
            savedDepartment.getId(),
            savedDepartment.getDepartmentName(),
            savedDepartment.getDepartmentDescription(),
            savedDepartment.getDepartmentCode()
        );
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        return new DepartmentDto(
            department.getId(),
            department.getDepartmentName(),
            department.getDepartmentName(),
            department.getDepartmentCode()
        );
    }
}
