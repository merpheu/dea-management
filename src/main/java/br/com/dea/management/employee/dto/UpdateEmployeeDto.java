package br.com.dea.management.employee.dto;

import br.com.dea.management.employee.EmployeeType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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

    private String name;

    @NotNull(message = "Email could not be null")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "Linkedin must not be empty")
    private String linkedin;

    private String password;


}
