package br.com.dea.management.project.controller;


import br.com.dea.management.academyclass.domain.AcademyClass;
import br.com.dea.management.academyclass.service.AcademyClassService;
import br.com.dea.management.employee.dto.EmployeeDto;

import br.com.dea.management.project.domain.Project;
import br.com.dea.management.project.dto.ProjectDto;
import br.com.dea.management.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
    @Slf4j
    @Tag(name = "Project", description = "The Project API")

    public class ProjectController {

        @Autowired
        ProjectService projectService;
        AcademyClassService academyClassService;

        @Operation(summary = "Load the list of projects paginated.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successful operation"),
                @ApiResponse(responseCode = "400", description = "Page or Page Size params not valid"),
                @ApiResponse(responseCode = "500", description = "Error fetching project list"),
        })
        @GetMapping("/project")
        public Page<ProjectDto> getProjects(@RequestParam(required = true) Integer page,
                                             @RequestParam(required = true) Integer pageSize) {

            log.info(String.format("Fetching projects : page : %s : pageSize", page, pageSize));

            Page<Project> projectsPaged = this.projectService.findAllProjectsPaginated(page, pageSize);
            Page<ProjectDto> projects = projectsPaged.map(project -> ProjectDto.fromProject(project));

            log.info(String.format("projects loaded successfully : projects : %s : pageSize", projects.getContent()));

            return projects;

        }

        @Operation(summary = "Load the project by ID.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successful operation"),
                @ApiResponse(responseCode = "400", description = "Project Id invalid"),
                @ApiResponse(responseCode = "404", description = "Project Not found"),
                @ApiResponse(responseCode = "500", description = "Error fetching project list"),
        })
        @GetMapping("/project/{id}")
        public ProjectDto getProjects(@PathVariable Long id) {

            log.info(String.format("Fetching projects by id : Id : %s", id));

           ProjectDto project = ProjectDto.fromProject(this.projectService.findProjectById(id));

            log.info(String.format("Project loaded successfully : Projects : %s", project));

            return project;

        }


    @Operation(summary = "Create a new project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Payload not valid"),
            @ApiResponse(responseCode = "500", description = "Error creating project"),
    })

    @PostMapping("/projects")
    public void createproject(@Valid @RequestBody ProjectDto projectDto) {
        log.info(String.format("Creating project : Payload : %s", projectDto));

       Project project  = projectService.createproject(projectDto);

        log.info(String.format("Project created successfully : id : %s", project.getId()));
    }
}
