package br.com.dea.management.user.repository;

import br.com.dea.management.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByEmail(String email); //automatic custom query
    public Optional<User> findByPassword(String password); //automatic custom query

    @Query("SELECT u FROM User u WHERE name = :name") //query
    public Optional<User> findByName(String name);

    @Query("SELECT l FROM User l WHERE linkedin = :linkedin") //query
    public Optional<User>findByLinkedin(String linkedin);


}
