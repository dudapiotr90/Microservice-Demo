package pl.dudi.organizationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dudi.organizationservice.dto.OrganizationDto;
import pl.dudi.organizationservice.service.OrganizationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(
        @RequestBody OrganizationDto organizationDto
    ) {
        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto> getOrganization(
        @PathVariable(name = "code") String organizationCode
    ) {
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }
}
