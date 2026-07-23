package de.lhind.internship.mini.project.common.DTOs.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRequestDTO {
    @NotBlank
    private String roomNumber;

    @Min(1)
    private int capacity;

    @NotNull
    @Positive
    private BigDecimal pricePerNight;

    @NotNull(message = "Room must have a status")
    private RoomStatus roomStatus;

    @NotNull(message = "Room must have a type")
    private RoomType roomType;
}
