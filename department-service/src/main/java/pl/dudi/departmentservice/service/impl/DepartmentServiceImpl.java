package pl.dudi.departmentservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.dudi.departmentservice.dto.DepartmentDto;
import pl.dudi.departmentservice.entity.Department;
import pl.dudi.departmentservice.exception.DepartmentCodeAlreadyExistException;
import pl.dudi.departmentservice.exception.DepartmentNotFoundException;
import pl.dudi.departmentservice.mappers.DepartmentMapper;
import pl.dudi.departmentservice.repository.DepartmentRepository;
import pl.dudi.departmentservice.service.DepartmentService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final ModelMapper modelMapper;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department optionalDepartment = departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode());
        if (Objects.nonNull(optionalDepartment)) {
            throw new DepartmentCodeAlreadyExistException(
                String.format("Department: [%s] already exist!", optionalDepartment.getDepartmentCode())
            );
        }
//        Department department = departmentMapper.mapToDepartment(departmentDto);
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);

//        return departmentMapper.mapToDepartmentDto(savedDepartment);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        if (Objects.isNull(department)) {
            throw new DepartmentNotFoundException(
                String.format("Department with code: [%s] doesn't exist!", departmentCode)
            );
        }

//        return departmentMapper.mapToDepartmentDto(department);
        return modelMapper.map(department, DepartmentDto.class);
    }
}
