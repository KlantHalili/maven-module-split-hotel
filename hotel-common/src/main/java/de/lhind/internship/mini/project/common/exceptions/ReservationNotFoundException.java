package de.lhind.internship.mini.project.common.exceptions;

public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException(Long id){
        super("Reservation with id: "+id+" doesn't exist");
    }
}
