package pl.dudi.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;
    @NotEmpty(message = "Department name is required to proceed")
    private String departmentName;
    @NotEmpty(message = "Description is required to proceed")
    private String departmentDescription;
    @NotEmpty(message = "Department code is required to proceed")
    private String departmentCode;
}
