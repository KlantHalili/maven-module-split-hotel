package de.lhind.internship.mini.project.common.DTOs.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestRequestDTO {
    @NotBlank(message = "Guest should have a name")
    private String name;

    @NotBlank(message = "Guest should have a last name")
    private String lastName;

    @NotBlank(message = "Guest should have an e-mail")
    @Email(message = "Should be of an e-mail format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^\\+?[0-9]{7,15}$",
            message = "Phone number must contain 7-15 digits and may start with '+'")
    private String phoneNumber;
}
