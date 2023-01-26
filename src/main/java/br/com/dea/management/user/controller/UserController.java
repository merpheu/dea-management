package br.com.dea.management.user.controller;

import br.com.dea.management.student.domain.Student;
import br.com.dea.management.student.dto.StudentDto;
import br.com.dea.management.student.service.StudentService;
import br.com.dea.management.user.domain.User;
import br.com.dea.management.user.dto.UserDto;
import br.com.dea.management.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
 @Autowired
    UserService userService;

 @Autowired
    StudentService studentService;

    @GetMapping("/user")
    public Page<UserDto> getUser(@RequestParam Integer page,
                                 @RequestParam Integer pageSize){


       Page<User> userPaged = this.userService.findAllUsersPaginated(page, pageSize);
       Page<UserDto> users = userPaged.map(user -> UserDto.fromUser(user));
       return users;

    }
}
