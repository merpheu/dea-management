package br.com.dea.management.employee.dto;


import br.com.dea.management.employee.EmployeeType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateEmployeeDto {

    @NotNull(message = "description could not be null")
    private String description;

    private String seniority;

    private EmployeeType employeeType;

}
