package br.com.dea.management.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class CreateStudentRequestDto {

        @NotNull(message = "Name could not be null")
        private String name;

        @NotNull(message = "Email could not be null")
        @Email(message = "Email must be valid")
        private String email;
        @NotEmpty(message = "Linkedin must not be empty")
        private String linkedin;

        private String university;

        private String graduation;

        private LocalDate finishDate;

        @NotNull(message = "Password could not be null")
        private String password;

    }
