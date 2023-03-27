package br.com.dea.management.academyclass.dto;


import br.com.dea.management.academyclass.ClassType;
import br.com.dea.management.academyclass.domain.AcademyClass;
import br.com.dea.management.employee.EmployeeType;
import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.employee.dto.EmployeeDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateClassDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private ClassType classType;
    private Employee instructor;


}
