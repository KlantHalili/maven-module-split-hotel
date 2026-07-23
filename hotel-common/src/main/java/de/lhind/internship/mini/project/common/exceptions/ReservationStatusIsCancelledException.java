package de.lhind.internship.mini.project.common.exceptions;

public class ReservationStatusIsCancelledException extends RuntimeException{
    public ReservationStatusIsCancelledException(Long id){
        super("Reservation with id: "+id+" is cancelled, and we can't undo its status");
    }
}
