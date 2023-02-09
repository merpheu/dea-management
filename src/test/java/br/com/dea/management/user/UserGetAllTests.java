package br.com.dea.management.user;


import br.com.dea.management.student.domain.Student;
import br.com.dea.management.student.repository.StudentRepository;
import br.com.dea.management.user.domain.User;
import br.com.dea.management.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class UserGetAllTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void beforeEach() {
        log.info("Before each test in " + UserGetAllTests.class.getSimpleName());
    }

    @BeforeAll
    void beforeSuiteTest() {
        log.info("Before all tests in " + UserGetAllTests.class.getSimpleName());
    }

    @Test
    void whenRequestingUserList_thenReturnListOfUsers() throws Exception {
        this.studentRepository.deleteAll();
        this.createFakeStudents(100);

        mockMvc.perform(get("/user?page=0&pageSize=4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(4)))
                .andExpect(jsonPath("$.content[0].name", is("name 0")))
                .andExpect(jsonPath("$.content[0].email", is("email 0")))
                .andExpect(jsonPath("$.content[0].linkedin", is("linkedin 0")))
                .andExpect(jsonPath("$.content[3].name", is("name 3")))
                .andExpect(jsonPath("$.content[3].email", is("email 3")))
                .andExpect(jsonPath("$.content[3].linkedin", is("linkedin 3")));
    }

    @Test
    void whenRequestingUserListAndPageQueryParamIsInvalid_thenReturnBadRequestError() throws Exception {
        mockMvc.perform(get("/user?page=xx&pageSize=4"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)));
    }

    @Test
    void whenRequestingGetListAndPageQueryParamIsMissing_thenReturnBadRequestError() throws Exception {
        mockMvc.perform(get("/user?pageSize=4"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)));
    }

    private void createFakeStudents(int amount) {
        for (int i = 0; i < amount; i++) {
            User u = new User();
            u.setEmail("email " + i);
            u.setName("name " + i);
            u.setLinkedin("linkedin " + i);
            u.setPassword("pwd " + i);



            Student student = Student.builder()
                    .university("UNI " + i)
                    .graduation("Grad " + i)
                    .finishDate(LocalDate.now())
                    .user(u)
                    .build();

            this.studentRepository.save(student);
        }
    }
}