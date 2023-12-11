package pl.dudi.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;

@Schema(
    description = "DepartmentDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    @Schema(
        description = "Department Name"
    )
    @NotEmpty(message = "Department name is required to proceed")
    private String departmentName;

    @Schema(
        description = "Department Description"
    )
    @NotEmpty(message = "Description is required to proceed")
    private String departmentDescription;

    @Schema(
        description = "Department Code"
    )
    @NotEmpty(message = "Department code is required to proceed")
    private String departmentCode;
}
