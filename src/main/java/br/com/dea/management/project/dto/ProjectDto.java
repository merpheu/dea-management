package br.com.dea.management.project.dto;


import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.project.domain.Project;
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
    private String pm_external;
    private LocalDate startDate;
    private LocalDate endDate;
    private Employee employee;


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
        projectDto.setEndDate(project.getEndDate());
        projectDto.setStartDate(project.getStartDate());
        projectDto.setPm_external(project.getPm_external());
        projectDto.setEmployee(project.getEmployeeType());

        return projectDto;
    }
}