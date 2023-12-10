package pl.dudi.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.dudi.employeeservice.dto.DepartmentDto;
import pl.dudi.employeeservice.dto.OrganizationDto;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationAPIClient {
    @GetMapping("api/organizations/{code}")
    OrganizationDto getOrganization(@PathVariable(name = "code") String organizationCode);

}
