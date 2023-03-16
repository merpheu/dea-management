package br.com.dea.management.employee.service;

import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.employee.dto.CreateEmployeeDto;
import br.com.dea.management.employee.dto.UpdateEmployeeDto;
import br.com.dea.management.employee.repository.EmployeeRepository;
import br.com.dea.management.exceptions.NotFoundException;
import br.com.dea.management.position.domain.Position;
import br.com.dea.management.position.repository.PositionRepository;
import br.com.dea.management.student.domain.Student;
import br.com.dea.management.student.dto.CreateStudentRequestDto;
import br.com.dea.management.student.dto.UpdateStudentRequestDto;
import br.com.dea.management.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;


    public List<Employee> findAllEmployees() {
        return this.employeeRepository.findAll();
    }

    public Page<Employee> findAllEmployeesPaginated(Integer page, Integer pageSize) {
        return this.employeeRepository.findAllPaginated(PageRequest.of(page, pageSize, Sort.by("user.name").ascending()));
    }

    public Employee findEmployeeById(Long id) {
        return this.employeeRepository.findById(id).orElseThrow(() -> new NotFoundException(Employee.class, id));
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = this.findEmployeeById(employeeId);
        this.employeeRepository.delete(employee);
    }

    public Employee createEmployee(CreateEmployeeDto createEmployeeDto) {
        Position position = this.positionRepository.findById(createEmployeeDto.getPosition())
                .orElseThrow(() -> new NotFoundException(Position.class, createEmployeeDto.getPosition()));

        User user = User.builder()
                .name(createEmployeeDto.getName())
                .email(createEmployeeDto.getEmail())
                .password(createEmployeeDto.getPassword())
                .linkedin(createEmployeeDto.getLinkedin())
                .build();

        Employee employee = Employee.builder()
                .user(user)
                .employeeType(createEmployeeDto.getEmployeeType())
                .position(position)
                .build();

        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long employeeId, UpdateEmployeeDto updateEmployeeDto) {
        Employee employee = this.findEmployeeById(employeeId);
        Position position = this.positionRepository.findById(updateEmployeeDto.getPosition())
                .orElseThrow(() -> new NotFoundException(Position.class, updateEmployeeDto.getPosition()));

        User user = employee.getUser();

        user.setName(updateEmployeeDto.getName());
        user.setEmail(updateEmployeeDto.getEmail());
        user.setPassword(updateEmployeeDto.getPassword());
        user.setLinkedin(updateEmployeeDto.getLinkedin());

        employee.setUser(user);
        employee.setEmployeeType(updateEmployeeDto.getEmployeeType());
        employee.setPosition(position);


        return this.employeeRepository.save(employee);
    }



}
