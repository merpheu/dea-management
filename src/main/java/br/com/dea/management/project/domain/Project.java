package br.com.dea.management.project.domain;

import br.com.dea.management.employee.EmployeeType;
import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.members.domain.Members;
import br.com.dea.management.members.dto.MemberDto;
import br.com.dea.management.position.domain.Position;
import br.com.dea.management.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String project_name;

    @Column
    private String project_client;

    @Column
    private String pm_external;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")

    private Employee employee_id;









}
