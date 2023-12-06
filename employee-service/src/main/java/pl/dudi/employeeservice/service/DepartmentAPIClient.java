package pl.dudi.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.dudi.employeeservice.dto.DepartmentDto;
import pl.dudi.employeeservice.dto.OrganizationDto;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentAPIClient {
    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable(name = "department-code") String departmentCode);

}
