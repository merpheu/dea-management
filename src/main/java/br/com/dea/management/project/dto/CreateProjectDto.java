package br.com.dea.management.project.dto;

import br.com.dea.management.employee.EmployeeType;
import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.members.domain.Members;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateProjectDto
{

    @NotNull(message = "Name could not be null")
    private String name;

    private String client;

    private String pm_external;

    @NotNull(message = "start_date could not be null")
    private LocalDate startDate;

    @NotNull(message = "end_date could not be null")
    private LocalDate endDate;

    private String po;
    private String sm;



}
