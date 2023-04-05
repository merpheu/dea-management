package br.com.dea.management.project.service;

import br.com.dea.management.academyclass.domain.AcademyClass;
import br.com.dea.management.academyclass.dto.CreateClassDto;
import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.employee.repository.EmployeeRepository;
import br.com.dea.management.exceptions.NotFoundException;
import br.com.dea.management.project.domain.Project;
import br.com.dea.management.project.dto.CreateProjectDto;
import br.com.dea.management.project.dto.ProjectDto;
import br.com.dea.management.project.repository.ProjectRepository;

import br.com.dea.management.student.repository.StudentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    private ProjectService projectService;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Project> findAllProjects() {
        return this.projectRepository.findAll();
    }

    public Project findProjectById(Long id) {
        return this.projectRepository.findById(id).orElseThrow(() -> new NotFoundException(Project.class, id));
    }

    public Page<Project> findAllProjectsPaginated(Integer page, Integer pageSize) {
        return this.projectRepository.findAllPaginated(PageRequest.of(page, pageSize))  ; //, Sort.by("project.name").ascending()));
    }


    public Project createproject(ProjectDto projectDto) {

        Employee employee = employeeRepository.findById(projectDto.getId())
                .orElseThrow(() -> new NotFoundException(Employee.class, projectDto.getId()));

        Project project1 = Project.builder()

                .name(projectDto.getName())
                .startDate(projectDto.getStartDate())
                .endDate(projectDto.getEndDate())
                .client(projectDto.getClient())
                .pm_external(projectDto.getPm_external())
                .employee(employee)

                .build();

        return this.projectRepository.save(project1);

    }

    public void deleteProject(Long projectId) {
        Project project = this.findProjectById(projectId);
        this.projectRepository.delete(project);
    }


}