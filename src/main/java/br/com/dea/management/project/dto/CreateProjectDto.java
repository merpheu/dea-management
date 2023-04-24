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

    @NotNull(message = "Client could not be null")
    private String client;

    @NotNull(message = "ExternalPm could not be null")
    private String externalPm;

    @NotNull(message = "Start date could not be null")
    private LocalDate startDate;

    @NotNull(message = "End date could not be null")
    private LocalDate endDate;

    @NotNull(message = "PO could not be null")
    private Long po;

    @NotNull(message = "SM could not be null")
    private Long sm;



}
