package de.lhind.internship.mini.project.common.exceptions;

public class RoomHasReservationsException extends RuntimeException{
    public RoomHasReservationsException(Long roomId){
        super("Room has some reservations, please take care of them before taking actions with the room");
    }
}
