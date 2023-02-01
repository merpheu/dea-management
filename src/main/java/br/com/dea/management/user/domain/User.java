package br.com.dea.management.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "myQuery", query = "SELECT u FROM User u where u.name= :name") //named query
@NamedQuery(name = "myQuery1", query = "SELECT p FROM User p where p.password= :password") //named query
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String linkedin;

}
