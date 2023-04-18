package br.com.dea.management.project.dto;


import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.employee.dto.EmployeeDto;
import br.com.dea.management.members.domain.Members;
import br.com.dea.management.members.dto.MemberDto;
import br.com.dea.management.position.domain.Position;
import br.com.dea.management.position.dto.PositionDto;
import br.com.dea.management.project.domain.Project;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDto {


    private Long id;
    private String project_name;
    private String project_client;
    private String pm_external;
    private LocalDate start_Date;
    private LocalDate end_Date;

    private EmployeeDto PO;
    private EmployeeDto SM;


    public static List<ProjectDto> fromProject (List<Project> projects) {
        return projects.stream().map(project -> {
            ProjectDto projectDto = ProjectDto.fromProject(project);
            return projectDto;
        }).collect(Collectors.toList());
    }


    public static ProjectDto fromProject(Project project) {
       ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setProject_name(project.getProject_name());
        projectDto.setProject_client(project.getProject_client());
        projectDto.setStart_Date(project.getStartDate());
        projectDto.setEnd_Date(project.getEndDate());
        projectDto.setPm_external(project.getPm_external());
        projectDto.setPO(EmployeeDto.fromEmployee(project.getEmployee_id()));
        projectDto.setSM(EmployeeDto.fromEmployee(project.getEmployee_id()));

//        Employee employee = project.getEmployee_id();
//        projectDto.setPO(EmployeeDto.fromEmployee(employee));
//        projectDto.setSM(EmployeeDto.fromEmployee(employee));

        return projectDto;
    }
}