package br.com.dea.management.student;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StudentCreationSuccessCaseTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    void whenRequestingStudentCreationWithAValidPayload_thenCreateAStudentSuccessfully() throws Exception {
        this.studentRepository.deleteAll();

        String payload = "{" +
                "\"name\": \"name\"," +
                "\"email\": \"email@eu.com\"," +
                "\"linkedin\": \"linkedin\"," +
                "\"university\": \"university\"," +
                "\"graduation\": \"graduation\"," +
                "\"password\": \"password\"," +
                "\"finishDate\": \"2023-02-27\"" +
                "}";
        mockMvc.perform(post("/student")
                        .contentType(APPLICATION_JSON_UTF8).content(payload))
                .andExpect(status().isOk());

        Student student = this.studentRepository.findAll().get(0);

        assertThat(student.getUser().getName()).isEqualTo("name");
        assertThat(student.getUser().getEmail()).isEqualTo("email@eu.com");
        assertThat(student.getUser().getLinkedin()).isEqualTo("linkedin");
        assertThat(student.getUser().getLinkedin()).isNotEmpty();
        assertThat(student.getUser().getPassword()).isEqualTo("password");
        assertThat(student.getGraduation()).isEqualTo("graduation");
        assertThat(student.getUniversity()).isEqualTo("university");

    }

    @Test
    void whenRequestingStudentCreationWithNotEmptyValues_thenCreateAStudentSuccessfully() throws Exception {
        this.studentRepository.deleteAll();

        String payload = "{" +
                "\"name\": \"name\"," +
                "\"email\": \"email@eu.com\"," +
                "\"linkedin\": \"linkedin\"," +
                "\"university\": \"university\"," +
                "\"graduation\": \"graduation\"," +
                "\"password\": \"password\"," +
                "\"finishDate\": \"2023-02-27\"" +
                "}";
        mockMvc.perform(post("/student")
                        .contentType(APPLICATION_JSON_UTF8).content(payload))
                .andExpect(status().isOk());

        Student student = this.studentRepository.findAll().get(0);

        assertThat(student.getUser().getName()).isEqualTo("name");
        assertThat(student.getUser().getName()).isNotEmpty();
        assertThat(student.getUser().getEmail()).isEqualTo("email@eu.com");
        assertThat(student.getUser().getEmail()).isNotEmpty();
        assertThat(student.getUser().getLinkedin()).isEqualTo("linkedin");
        assertThat(student.getUser().getLinkedin()).isNotEmpty();
        assertThat(student.getUser().getPassword()).isEqualTo("password");
        assertThat(student.getGraduation()).isEqualTo("graduation");
        assertThat(student.getGraduation()).isNotEmpty();
        assertThat(student.getUniversity()).isEqualTo("university");
        assertThat(student.getUniversity()).isNotEmpty();

    }
}