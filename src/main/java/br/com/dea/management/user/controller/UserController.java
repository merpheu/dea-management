package br.com.dea.management.user.controller;

import br.com.dea.management.student.domain.Student;
import br.com.dea.management.student.dto.StudentDto;
import br.com.dea.management.student.service.StudentService;
import br.com.dea.management.user.domain.User;
import br.com.dea.management.user.dto.UserDto;
import br.com.dea.management.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
 @Autowired
    UserService userService;

    @GetMapping("/user")
    public Page<UserDto> getUser(@RequestParam Integer page,
                                 @RequestParam Integer pageSize){
         log.info(String.format("Fetching users : page : %s : pageSize", page, pageSize));
         Page<User> userPaged = this.userService.findAllUsersPaginated(page, pageSize);
         Page<UserDto> users = userPaged.map(user -> UserDto.fromUser(user));
         log.info(String.format("users loaded successfully : Students : %s : pageSize", users.getContent()));
       return users;
    }

    @GetMapping("user/{id}")
    public UserDto findById (@PathVariable Long id){
        log.info(String.format("Fetching student by id : Id : %s", id));
        UserDto user = UserDto.fromUser(this.userService.findUserById(id));
        log.info(String.format("user loaded successfully : User : %s", id));
        return user;

    }
}
