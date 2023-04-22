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
    private String name;
    private String client;
    private String externalPm;
    private LocalDate start_Date;
    private LocalDate end_Date;

    private EmployeeDto po;
    private EmployeeDto sm;


    public static List<ProjectDto> fromProject (List<Project> projects) {
        return projects.stream().map(project -> {
            ProjectDto projectDto = ProjectDto.fromProject(project);
            return projectDto;
        }).collect(Collectors.toList());
    }

    public static ProjectDto fromProject(Project project) {
       ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setClient(project.getClient());
        projectDto.setStart_Date(project.getStartDate());
        projectDto.setEnd_Date(project.getEndDate());
        projectDto.setExternalPm(project.getExternalPm());

        projectDto.setPo(EmployeeDto.fromEmployee(project.getPo()));
        projectDto.setSm(EmployeeDto.fromEmployee(project.getSm()));

        return projectDto;
    }
}