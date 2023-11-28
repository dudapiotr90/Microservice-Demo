package pl.dudi.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    @NotEmpty(message = "First name is required to proceed")
    private String firstName;
    @NotEmpty(message = "Last name is required to proceed")
    private String lastName;
    @NotEmpty(message = "Email address is required to proceed")
    @Email(message = "Email address should be correct")
    private String email;

    private String departmentCode;

}