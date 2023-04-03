package br.com.dea.management.project.service;

import br.com.dea.management.exceptions.NotFoundException;
import br.com.dea.management.project.domain.Project;
import br.com.dea.management.project.repository.ProjectRepository;

import br.com.dea.management.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAllProjects() {
        return this.projectRepository.findAll();
    }

    public Project findProjectById(Long id) {
        return this.projectRepository.findById(id).orElseThrow(() -> new NotFoundException(Project.class, id));
    }

    public Page<Project> findAllProjectsPaginated(Integer page, Integer pageSize) {
        return this.projectRepository.findAllPaginated(PageRequest.of(page, pageSize, Sort.by("project.name").ascending()));
    }

}