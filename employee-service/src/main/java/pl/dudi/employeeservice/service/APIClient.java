package pl.dudi.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.dudi.employeeservice.dto.DepartmentDto;

@FeignClient(url = "http://localhost:8080",value = "DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable(name = "department-code") String departmentCode);
}
