package br.com.dea.management.user.service;


import br.com.dea.management.exceptions.NotFoundException;
import br.com.dea.management.user.domain.User;
import br.com.dea.management.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }
    public User findUserByEmail(String email){
        Optional<User> user = this.userRepository.findByEmail(email);
        return user.orElseThrow(() -> new NotFoundException(User.class, email));
    }

    public User findUserByPwd(String password){
        Optional<User> user = this.userRepository.findByPassword(password);
        return user.orElseThrow(() -> new NotFoundException(User.class, password));
    }

    public Page<User> findAllUsersPaginated(Integer page, Integer pageSize) {
        return this.userRepository.findAllUsersPaginated(PageRequest.of(page, pageSize));
    }

    public User findUserById(Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
    }
}
