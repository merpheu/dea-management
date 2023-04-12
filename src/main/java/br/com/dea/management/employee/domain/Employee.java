package br.com.dea.management.employee.domain;

import br.com.dea.management.academyclass.domain.AcademyClass;
import br.com.dea.management.employee.EmployeeType;
import br.com.dea.management.members.domain.Members;
import br.com.dea.management.position.domain.Position;
import br.com.dea.management.project.domain.Project;
import br.com.dea.management.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}
