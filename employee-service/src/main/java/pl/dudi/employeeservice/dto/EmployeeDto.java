package pl.dudi.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Schema(
    description = "EmployeeDto model information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;

    @Schema(
        description = "Employee First Name"
    )
    @NotEmpty(message = "First name is required to proceed")
    private String firstName;

    @Schema(
        description = "Employee Last Name",
        pattern = "**@**"
    )
    @NotEmpty(message = "Last name is required to proceed")
    private String lastName;

    @Schema(
        description = "Employee Email"
    )
    @NotEmpty(message = "Email address is required to proceed")
    @Email(message = "Email address should be correct")
    private String email;

    @Schema(
        description = "Department Code that Employee Belongs To"
    )
    private String departmentCode;

    @Schema(
        description = "Organization Code that Employee Belongs To"
    )
    private String organizationCode;

}