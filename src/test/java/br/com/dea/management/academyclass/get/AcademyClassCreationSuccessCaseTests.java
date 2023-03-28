package br.com.dea.management.academyclass.get;

import br.com.dea.management.AcademyTestUtils;
import br.com.dea.management.academyclass.ClassType;
import br.com.dea.management.academyclass.domain.AcademyClass;
import br.com.dea.management.academyclass.repository.AcademyClassRepository;
import br.com.dea.management.employee.EmployeeTestUtils;
import br.com.dea.management.employee.EmployeeType;
import br.com.dea.management.employee.domain.Employee;
import br.com.dea.management.employee.repository.EmployeeRepository;
import br.com.dea.management.position.domain.Position;
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
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AcademyClassCreationSuccessCaseTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AcademyClassRepository acmRepository;
    private AcademyTestUtils academyTestUtils;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    void whenRequestingAcademyCreationWithAValidPayload_thenCreateAcademyClassSuccessfully() throws Exception {
        this.acmRepository.deleteAll();

        LocalDate startDate = LocalDate.of(2023, Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(2024, Month.DECEMBER, 20);
        this.academyTestUtils.createFakeClass(1, startDate, endDate, ClassType.DEVELOPER);

        //Employee employee = academyTestUtils.createFakeClass(10,startDate,endDate);

        String payload = "{" +
                "\"startDate\": \"2023-03-23\"," +
                "\"endDate\": \"2023-03-23\"," +
             //   "\"instructor\": " +employee.getEmployeeType()+
                "}";
        mockMvc.perform(post("/Academy-class")
                        .contentType(APPLICATION_JSON_UTF8).content(payload))
                .andExpect(status().isOk());

        AcademyClass acm = this.acmRepository.findAll().get(0);

        assertThat(acm.getEndDate().getDayOfMonth()).isEqualTo("name");
        assertThat(acm.getStartDate().getDayOfMonth()).isEqualTo("name");
        assertThat(acm.getClassType()).isEqualTo(ClassType.DEVELOPER);


    }
}