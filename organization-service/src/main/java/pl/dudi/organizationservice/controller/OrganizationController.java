package pl.dudi.organizationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dudi.organizationservice.dto.OrganizationDto;
import pl.dudi.organizationservice.service.OrganizationService;
@Tag(
    name = "Organization Service - OrganizationController",
    description = "OrganizationController Exposes REST APIs for Organization-Service"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Operation(
        summary = "Save Organization REST API",
        description = "Save Organization REST API is used to save organization object into database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP STATUS 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(
        @RequestBody OrganizationDto organizationDto
    ) {
        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get Organization REST API",
        description = "Get Organization REST API is used to get organization from database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP STATUS 200 SUCCESS"
    )
    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto> getOrganization(
        @PathVariable(name = "code") String organizationCode
    ) {
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }
}
