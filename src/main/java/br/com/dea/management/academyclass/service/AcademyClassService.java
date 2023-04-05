package br.com.dea.management.academyclass.service;

import br.com.dea.management.academyclass.domain.AcademyClass;
import br.com.dea.management.academyclass.dto.CreateClassDto;
import br.com.dea.management.academyclass.repository.AcademyClassRepository;
import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.employee.dto.CreateEmployeeRequestDto;
import br.com.dea.management.employee.dto.UpdateEmployeeRequestDto;
import br.com.dea.management.employee.repository.EmployeeRepository;
import br.com.dea.management.exceptions.NotFoundException;
import br.com.dea.management.position.domain.Position;
import br.com.dea.management.position.repository.PositionRepository;
import br.com.dea.management.student.domain.Student;
import br.com.dea.management.student.repository.StudentRepository;
import br.com.dea.management.user.domain.User;
import br.com.dea.management.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AcademyClassService {

    @Autowired
    private AcademyClassRepository academyClassRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;



    public Page<AcademyClass> findAllAcademyClassPaginated(Integer page, Integer pageSize) {
        return this.academyClassRepository.findAllPaginated(PageRequest.of(page, pageSize, Sort.by("startDate").ascending()));
    }

    public AcademyClass findAcademyClassById(Long id) {
        return this.academyClassRepository.findById(id).orElseThrow(() -> new NotFoundException(AcademyClass.class, id));
    }


    public AcademyClass createAcademyclass(CreateClassDto createClassDto) {

       Employee employee = employeeRepository.findById(createClassDto.getInstructorId())
             .orElseThrow(() -> new NotFoundException(Employee.class, createClassDto.getInstructorId()));

                 AcademyClass academyclass = AcademyClass.builder()
                .startDate(createClassDto.getStartDate())
                .endDate(createClassDto.getEndDate())
                         .instructor(employee)
                         .classType(createClassDto.getClassType())
                .build();
        return this.academyClassRepository.save(academyclass);

    }
    public AcademyClass updateAcademyClass (Long classId, CreateClassDto createClassDto) {
        AcademyClass academyClass = this.findAcademyClassById(classId);

        Employee employee = employeeRepository.findById(createClassDto.getInstructorId())
              .orElseThrow(() -> new NotFoundException(Employee.class, createClassDto.getInstructorId()));

        academyClass.setEndDate(createClassDto.getEndDate());
        academyClass.setStartDate(createClassDto.getStartDate());
        academyClass.setInstructor(employee);
        academyClass.setClassType(createClassDto.getClassType());
        return this.academyClassRepository.save(academyClass);

    }

}
