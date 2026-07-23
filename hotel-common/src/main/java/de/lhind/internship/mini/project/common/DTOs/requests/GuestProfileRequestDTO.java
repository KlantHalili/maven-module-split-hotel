package de.lhind.internship.mini.project.common.DTOs.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestProfileRequestDTO {
    @NotBlank(message = "Guests should have an address")
    private String address;

    @NotNull(message = "Guests should have a birthday")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Guests should have a nationality")
    private String nationality;

    private String preferredLanguage;
}
