package pl.dudi.departmentservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dudi.departmentservice.dto.DepartmentDto;
import pl.dudi.departmentservice.service.DepartmentService;

import jakarta.validation.Valid;
@Tag(
    name = "Department Service - Department Controller",
    description = "Department Controller Exposes REST APIs for Department-Service"
)
@RestController
@RequestMapping("api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @Operation(
        summary = "Save Department REST API",
        description = "Save Department REST API is used to save department object in database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP STATUS 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(
        @Valid @RequestBody DepartmentDto departmentDto
    ) {
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get Department REST API",
        description = "Get Department REST API is used to get department object from database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP STATUS 200 SUCCESS"
    )
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(
        @PathVariable(name = "department-code") String departmentCode
    ) {
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return ResponseEntity.ok(departmentDto);
    }
}
