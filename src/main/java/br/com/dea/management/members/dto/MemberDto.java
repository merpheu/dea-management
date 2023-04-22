package br.com.dea.management.members.dto;

import br.com.dea.management.employee.dto.EmployeeDto;
import br.com.dea.management.project.dto.ProjectDto;
import lombok.*;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

        private Long id;
        private ProjectDto projectId;
        private EmployeeDto employeeId;
}

