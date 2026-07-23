package de.lhind.internship.mini.project.common.DTOs.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelRequestDTO {

    @NotBlank(message = "A hotel should have a name")
    private String name;

    @NotBlank(message = "Hotel should exist somewhere")
    private String city;

    @NotBlank(message = "There should be an address")
    private String address;

    private int hotelStarRating;
}
