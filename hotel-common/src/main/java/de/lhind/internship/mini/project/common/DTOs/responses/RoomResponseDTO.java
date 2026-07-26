package de.lhind.internship.mini.project.common.DTOs.responses;

import de.lhind.internship.mini.project.common.enums.RoomStatus;
import de.lhind.internship.mini.project.common.enums.RoomType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponseDTO {
    private Long id;
    private String roomNumber;
    private int capacity;
    private BigDecimal pricePerNight;
    private RoomStatus status;
    private RoomType roomType;
    private String hotelName;
}
