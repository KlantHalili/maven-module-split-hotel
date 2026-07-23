package de.lhind.internship.mini.project.common.DTOs.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelResponseDTO {

    private Long id;
    private String name;
    private String city;
    private String address;
    private int hotelStarRating;
}
