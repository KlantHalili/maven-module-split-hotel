package de.lhind.internship.mini.project.common.DTOs.responses;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestProfileResponseDTO {
    private Long id;
    private String address;
    private LocalDate dateOfBirth;
    private String nationality;
    private String preferredLanguage;
}
