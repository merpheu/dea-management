package br.com.dea.management;

import br.com.dea.management.user.domain.User;
import br.com.dea.management.user.repository.UserRepository;
import br.com.dea.management.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void run(String... args) throws Exception{
		this.userRepository.deleteAll();
		for (int i = 0; i<5; i++){
			User u = new User();
			u.setEmail("email " + i);
			u.setName("name " + i);
			u.setLinkedin("linkedin " + i);
			u.setPassword("pwd " + i);
			this.userRepository.save(u);
		}

		List<User> users = this.userService.findAllUsers();
		users.forEach(user -> System.out.println("Name  " + user.getName()));

		Optional<User> loadedByuserName = this.userRepository.findByName("name 1");
		System.out.println("User name 1 (From @Query) name:" + loadedByuserName.get().getName());

		Optional<User> lkd = this.userRepository.findByLinkedin("linkedin 4");
		System.out.println("User (From @Query linkedin:"+ lkd.get().getLinkedin());

		TypedQuery<User> q = entityManager.createNamedQuery("myQuery", User.class);
		q.setParameter("name", "name 2");
		User userFromNamedQuery = q.getResultList().get(0);
		System.out.println("User name 2 (From NamedQuery) name: " + userFromNamedQuery.getName());

		TypedQuery<User> x = entityManager.createNamedQuery("myQuery1", User.class);
		x.setParameter("password", "pwd 4");
		User userFromNamedQuery1 = x.getResultList().get(0);
		System.out.println("User password (From NamedQuery1) password: " + userFromNamedQuery1.getPassword());

		//Loading user by email
		User loadedUser = this.userService.findUserByEmail("email 1");
		System.out.println("User email 1 name: " + loadedUser.getName());

         //Loading user by pwd
		 User loadedPwd = this.userService.findUserByPwd("pwd 4");
		 System.out.println("User password 4 name:" + loadedPwd.getName());


		//Updating user name 1 linkedin
		loadedUser.setLinkedin("new linkedin");
		this.userRepository.save(loadedUser);

		}
	}


