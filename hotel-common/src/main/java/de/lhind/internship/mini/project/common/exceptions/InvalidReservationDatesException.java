package de.lhind.internship.mini.project.common.exceptions;

public class InvalidReservationDatesException extends RuntimeException {

    public InvalidReservationDatesException() {
        super("Check-out date must be after check-in date.");
    }
}
