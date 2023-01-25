package br.com.dea.management.student.service;

import br.com.dea.management.student.domain.Student;
import br.com.dea.management.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {


    @Autowired
    private StudentRepository studentrepository;

    public List<Student> findAllStudents(){return this.studentrepository.findAll();}

    public Page<Student> findAllStudentsPaginated(Integer page, Integer pageSize) {
        return this.studentrepository.findAllPaginated(PageRequest.of(page, pageSize));
    }
}
