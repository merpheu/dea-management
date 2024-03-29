package br.com.dea.management.employee;

import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.employee.repository.EmployeeRepository;
import br.com.dea.management.position.domain.Position;
import br.com.dea.management.position.repository.PositionRepository;
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

    private PositionRepository positionRepository;

    @Autowired
    private EmployeeTestUtils employeeTestUtils;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    void whenRequestingEmployeeUpdateWithAValidPayload_thenUpdateAEmployeeSuccessfully() throws Exception {
        this.employeeRepository.deleteAll();
        this.employeeTestUtils.createFakeEmployees(1);
        Position position = this.employeeTestUtils.createFakePosition("Designer", "Junior");

        Employee employeebase = this.employeeRepository.findAll().get(0);

        String payload = "{" +
                "\"name\": \"name\"," +
                "\"email\": \"email@eu.com\"," +
                "\"linkedin\": \"linkedin\"," +
                "\"employeeType\": \"DEVELOPER\"," +
                "\"position\": " + position.getId() + "," +
                "\"password\": \"password\"" +
                "}";
        mockMvc.perform(put("/employee/" + employeebase.getId())
                        .contentType(APPLICATION_JSON_UTF8).content(payload))
                .andExpect(status().isOk());

        Employee employee = this.employeeRepository.findAll().get(0);

        assertThat(employee.getUser().getName()).isEqualTo("name");
        assertThat(employee.getUser().getEmail()).isEqualTo("email@eu.com");
        assertThat(employee.getUser().getLinkedin()).isEqualTo("linkedin");
        assertThat(employee.getUser().getPassword()).isEqualTo("password");
        assertThat(employee.getEmployeeType()).isEqualTo(EmployeeType.DEVELOPER);
        assertThat(employee.getPosition().getId()).isEqualTo(position.getId());
    }
}
