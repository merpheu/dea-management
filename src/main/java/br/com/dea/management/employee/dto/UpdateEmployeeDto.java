package br.com.dea.management.employee.dto;

import br.com.dea.management.employee.EmployeeType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateEmployeeDto {

    @NotNull(message = "description could not be null")
    private String description;

    private String seniority;

    private EmployeeType employeeType;

}
