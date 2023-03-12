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
        Position position = this.positionRepository.findById(createEmployeeDto.getPositionID()).get();
        User user = User.builder()
                .email(createEmployeeDto.getEmail())
                .name(createEmployeeDto.getName())
                .linkedin(createEmployeeDto.getLinkedin())
                .password(createEmployeeDto.getPassword())
                .build();

        Employee employee = Employee.builder()
                .position(position)
                .user(user)
                .employeeType(createEmployeeDto.getEmployeeType())
                .build();

        return this.employeeRepository.save(employee);
    }



    public Employee updateEmployee(Long employeeId, UpdateEmployeeDto updateEmployeeDto) {
        Employee employee = this.findEmployeeById(employeeId);
        Position position = employee.getPosition();
        User user = employee.getUser();

        employee.setUser(user);
        employee.setEmployeeType(updateEmployeeDto.getEmployeeType());
        employee.setPosition(position);
        position.setDescription(updateEmployeeDto.getDescription());
        position.setSeniority(updateEmployeeDto.getSeniority());

        return this.employeeRepository.save(employee);
    }

}
