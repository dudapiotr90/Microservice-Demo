package pl.dudi.employeeservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dudi.employeeservice.dto.APIResponseDto;
import pl.dudi.employeeservice.dto.EmployeeDto;
import pl.dudi.employeeservice.service.EmployeeService;

import jakarta.validation.Valid;

@Tag(
    name = "Employee Service - EmployeeController",
    description = "EmployeeController Exposes REST APIs for Employee-Service"
)
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(
        summary = "Save Employee REST API",
        description = "Save Employee REST API is used to save employee object into database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP STATUS 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(
    @Valid @RequestBody EmployeeDto employeeDto
    ) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get Employee REST API",
        description = "Get Employee REST API is used to get employee from database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP STATUS 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployee(
        @PathVariable(name = "id") Long employeeId
    ) {
        APIResponseDto employee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employee);
    }
}
