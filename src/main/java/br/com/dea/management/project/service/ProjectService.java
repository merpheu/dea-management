package br.com.dea.management.project.service;

import br.com.dea.management.academyclass.domain.AcademyClass;
import br.com.dea.management.academyclass.dto.CreateClassDto;
import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.employee.dto.EmployeeDto;
import br.com.dea.management.employee.repository.EmployeeRepository;
import br.com.dea.management.exceptions.NotFoundException;
import br.com.dea.management.members.domain.Members;
import br.com.dea.management.members.dto.MemberDto;
import br.com.dea.management.members.repository.MembersRepository;
import br.com.dea.management.position.domain.Position;
import br.com.dea.management.position.dto.PositionDto;
import br.com.dea.management.position.repository.PositionRepository;
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
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MembersRepository membersRepository;
    @Autowired
    private PositionRepository positionRepository;

    public List<Project> findAllProjects() {
        return this.projectRepository.findAll();
    }

    public Project findProjectById(Long id) {
        return this.projectRepository.findById(id).orElseThrow(() -> new NotFoundException(Project.class, id));
    }

    public Page<Project> findAllProjectsPaginated(Integer page, Integer pageSize) {
        return this.projectRepository.findAllPaginated(PageRequest.of(page, pageSize))  ; //, Sort.by("project.name").ascending()));
    }


    public Project createproject(CreateProjectDto createProjectDto) {

        Employee employeePO = employeeRepository.findById(createProjectDto.getPo().getId())
                .orElseThrow(() -> new NotFoundException(Employee.class, createProjectDto.getPo().getId()));

        Employee employeeSM = employeeRepository.findById(createProjectDto.getSm().getId())
                .orElseThrow(() -> new NotFoundException(Employee.class, createProjectDto.getSm().getId()));

        Project project1 = Project.builder()

                .project_name(createProjectDto.getProject_name())
                .startDate(createProjectDto.getStart_Date())
                .endDate(createProjectDto.getEnd_Date())
                .project_client(createProjectDto.getProject_client())
                .pm_external(createProjectDto.getPm_external())
                .employee_id(employeePO)
                .employee_id(employeeSM)
                .build();

        return this.projectRepository.save(project1);

    }

    public Project updateProject (Long ProjId, CreateProjectDto createProjectDto) {
        Project project = this.findProjectById(ProjId);

        Employee employeePO = employeeRepository.findById(createProjectDto.getPo().getId())
                .orElseThrow(() -> new NotFoundException(Employee.class, createProjectDto.getPo().getId()));

        Employee employeeSM = employeeRepository.findById(createProjectDto.getSm().getId())
                .orElseThrow(() -> new NotFoundException(Employee.class, createProjectDto.getSm().getId()));


        createProjectDto.setEnd_Date(createProjectDto.getEnd_Date());
        createProjectDto.setStart_Date(createProjectDto.getStart_Date());
        createProjectDto.setProject_client(createProjectDto.getProject_client());
        createProjectDto.setPm_external(createProjectDto.getPm_external());
        createProjectDto.setProject_name(createProjectDto.getProject_name());
        createProjectDto.setSm(employeeSM);
        createProjectDto.setPo(employeePO);

        return this.projectRepository.save(project);

    }

    public void deleteProject(Long projectId) {
        Project project = this.findProjectById(projectId);
        this.projectRepository.delete(project);
    }

}