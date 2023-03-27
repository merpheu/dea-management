package br.com.dea.management.academyclass.service;

import br.com.dea.management.academyclass.domain.AcademyClass;
import br.com.dea.management.academyclass.dto.CreateClassDto;
import br.com.dea.management.academyclass.repository.AcademyClassRepository;
import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.employee.dto.CreateEmployeeDto;
import br.com.dea.management.employee.repository.EmployeeRepository;
import br.com.dea.management.exceptions.NotFoundException;
import br.com.dea.management.position.domain.Position;
import br.com.dea.management.position.repository.PositionRepository;
import br.com.dea.management.student.domain.Student;
import br.com.dea.management.student.repository.StudentRepository;
import br.com.dea.management.user.domain.User;
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
    @Autowired
    private PositionRepository positionRepository;

    public Page<AcademyClass> findAllAcademyClassPaginated(Integer page, Integer pageSize) {
        return this.academyClassRepository.findAllPaginated(PageRequest.of(page, pageSize, Sort.by("startDate").ascending()));
    }

    public AcademyClass findAcademyClassById(Long id) {
        return this.academyClassRepository.findById(id).orElseThrow(() -> new NotFoundException(AcademyClass.class, id));
    }


    public AcademyClass createAcademyclass(CreateClassDto createClassDto) {

       Employee employee = employeeRepository.findById(createClassDto.getInstructor().getId())
             .orElseThrow(() -> new NotFoundException(Employee.class, createClassDto.getInstructor().getId()));

                 AcademyClass academyclass = AcademyClass.builder()
                .startDate(createClassDto.getStartDate())
                .endDate(createClassDto.getEndDate())
                         .instructor(createClassDto.getInstructor())
                         .classType(createClassDto.getClassType())
                .build();

        return this.academyClassRepository.save(academyclass);

    }
}
