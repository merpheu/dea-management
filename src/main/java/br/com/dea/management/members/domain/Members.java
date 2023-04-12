package br.com.dea.management.members.domain;

import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.project.domain.Project;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

   // private Project project_id;


 //   private Employee employee_id;

}



