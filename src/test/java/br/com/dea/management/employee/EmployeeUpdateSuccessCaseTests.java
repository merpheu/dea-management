package br.com.dea.management.employee;

import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.employee.repository.EmployeeRepository;
import br.com.dea.management.student.StudentTestUtils;
import br.com.dea.management.student.domain.Student;
import br.com.dea.management.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EmployeeUpdateSuccessCaseTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeTestUtils employeeTestUtils;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    void whenRequestingEmployeeUpdateWithAValidPayload_thenUpdateAEmployeeSuccessfully() throws Exception {
        this.employeeRepository.deleteAll();
        this.employeeTestUtils.createFakeEmployees(1);

        Employee employee = this.employeeRepository.findAll().get(0);

        String payload = "{" +
                "\"name\": \"name\"," +
                "\"email\": \"email@eu.com\"," +
                "\"linkedin\": \"linkedin\"," +
                "\"password\": \"password\"," +
                "\"description\": \"Seniority level\"," +
                "\"seniority\": \"Senior\"," +
                "\"employeeType\": \"DTL\"" +
                "}";
        mockMvc.perform(put("/employee/" + employee.getId())
                        .contentType(APPLICATION_JSON_UTF8).content(payload))
                .andExpect(status().isOk());

        Employee employee1 = this.employeeRepository.findAll().get(0);

        assertThat(employee1.getUser().getName()).isEqualTo("name 0");
        assertThat(employee1.getUser().getEmail()).isEqualTo("email@eu.com 0");
        assertThat(employee1.getUser().getLinkedin()).isEqualTo("linkedin 0");
        assertThat(employee1.getUser().getPassword()).isEqualTo("password 0");
        assertThat(employee1.getPosition().getSeniority()).isEqualTo("Senior");
        assertThat(employee1.getPosition().getDescription()).isEqualTo("Seniority level");
        assertThat(employee1.getEmployeeType()).isNotNull();
    }
}
