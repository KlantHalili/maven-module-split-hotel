package de.lhind.internship.mini.project.common.DTOs.requests;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequestDTO {
    @NotNull(message = "Please write a check in date")
    @FutureOrPresent(message = "Reservation can't be on the past")
    private LocalDate checkInDate;

    @NotNull(message = "Please write a check out date")
    private LocalDate checkOutDate;

    @Min(value = 1, message = "Number of guests must be at least 1")
    private int numberOfGuests;

    @NotNull(message = "A guest should do the reservation")
    private Long guestId;

    @NotNull(message = "A reservation must have a room id")
    private Long roomId;

}
