package pl.dudi.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dudi.employeeservice.dto.APIResponseDto;
import pl.dudi.employeeservice.dto.EmployeeDto;
import pl.dudi.employeeservice.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(
    @Valid @RequestBody EmployeeDto employeeDto
    ) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployee(
        @PathVariable(name = "id") Long employeeId
    ) {
        APIResponseDto employee = employeeService.getEmployee(employeeId);
        return ResponseEntity.ok(employee);
    }
}
