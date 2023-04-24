package br.com.dea.management.project.update;

import br.com.dea.management.academyclass.repository.AcademyClassRepository;
import br.com.dea.management.employee.EmployeeTestUtils;
import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.employee.repository.EmployeeRepository;
import br.com.dea.management.project.ProjectTestUtils;
import br.com.dea.management.project.domain.Project;
import br.com.dea.management.project.repository.ProjectRepository;
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

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProjectUpdatePayloadValidationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeTestUtils employeeTestUtils;

    @Autowired
    private AcademyClassRepository academyClassRepository;

    @Autowired
    private ProjectTestUtils projectTestUtils;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    void whenPayloadRequiredFieldsAreMissing_thenReturn400AndTheErrors() throws Exception {
        String payload = "{}";
        mockMvc.perform(put("/project/1")
                        .contentType(APPLICATION_JSON_UTF8).content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(7)))
                .andExpect(jsonPath("$.details[*].field", hasItem("startDate")))
                .andExpect(jsonPath("$.details[*].errorMessage", hasItem("Start date could not be null")))
                .andExpect(jsonPath("$.details[*].field", hasItem("endDate")))
                .andExpect(jsonPath("$.details[*].errorMessage", hasItem("End date could not be null")))
                .andExpect(jsonPath("$.details[*].field", hasItem("name")))
                .andExpect(jsonPath("$.details[*].errorMessage", hasItem("Name could not be null")))
                .andExpect(jsonPath("$.details[*].field", hasItem("externalPm")))
                .andExpect(jsonPath("$.details[*].errorMessage", hasItem("ExternalPm could not be null")))
                .andExpect(jsonPath("$.details[*].field", hasItem("po")))
                .andExpect(jsonPath("$.details[*].errorMessage", hasItem("PO could not be null")))
                .andExpect(jsonPath("$.details[*].field", hasItem("sm")))
                .andExpect(jsonPath("$.details[*].errorMessage", hasItem("SM could not be null")))
                .andExpect(jsonPath("$.details[*].field", hasItem("client")))
                .andExpect(jsonPath("$.details[*].errorMessage", hasItem("Client could not be null")));

    }

    @Test
    void whenRequestingProjectUpdateWithAValidPayloadButWithAProductOwnerThatDoesNotExistsDoesNotExists_thenReturn404Error() throws Exception {
        this.academyClassRepository.deleteAll();
        this.projectRepository.deleteAll();
        this.employeeRepository.deleteAll();

        this.projectTestUtils.createFakeProject(1);
        Project project = this.projectRepository.findAll().get(0);

        Employee employee = this.employeeRepository.findAll().get(0);

        String payload = "{" +
                "\"startDate\": \"2022-01-01\"," +
                "\"endDate\": \"2024-01-01\"," +
                "\"name\": \"name\"," +
                "\"client\": \"client\"," +
                "\"externalPm\": \"manager\"," +
                "\"sm\": " + employee.getId() + "," +
                "\"po\": 200000" +
                "}";
        mockMvc.perform(put("/project/" + project.getId())
                        .contentType(APPLICATION_JSON_UTF8).content(payload))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)));

    }

    @Test
    void whenRequestingProjectUpdateWithAValidPayloadButWithAScrumMasterThatDoesNotExistsDoesNotExists_thenReturn404Error() throws Exception {
        this.academyClassRepository.deleteAll();
        this.projectRepository.deleteAll();
        this.employeeRepository.deleteAll();

        this.projectTestUtils.createFakeProject(1);
        Project project = this.projectRepository.findAll().get(0);

        Employee employee = this.employeeRepository.findAll().get(0);

        String payload = "{" +
                "\"startDate\": \"2022-01-01\"," +
                "\"endDate\": \"2024-01-01\"," +
                "\"name\": \"name\"," +
                "\"client\": \"client\"," +
                "\"externalPm\": \"manager\"," +
                "\"po\": " + employee.getId() + "," +
                "\"sm\": 200000" +
                "}";
        mockMvc.perform(put("/project/" + project.getId())
                        .contentType(APPLICATION_JSON_UTF8).content(payload))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)));

    }

    @Test
    void whenRequestingProjectCreationWithAValidPayloadButWithAScrumMasterThatDoesNotExistsDoesNotExists_thenReturn404Error() throws Exception {
        this.academyClassRepository.deleteAll();
        this.projectRepository.deleteAll();
        this.employeeRepository.deleteAll();

        this.employeeTestUtils.createFakeEmployees(1);
        Employee employee = this.employeeRepository.findAll().get(0);

        String payload = "{" +
                "\"startDate\": \"2022-01-01\"," +
                "\"endDate\": \"2024-01-01\"," +
                "\"name\": \"name\"," +
                "\"client\": \"client\"," +
                "\"externalPm\": \"manager\"," +
                "\"po\": " + employee.getId() + "," +
                "\"sm\": 200000" +
                "}";
        mockMvc.perform(post("/project")
                        .contentType(APPLICATION_JSON_UTF8).content(payload))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.details").isArray())
                .andExpect(jsonPath("$.details", hasSize(1)));

    }

}