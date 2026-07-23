package de.lhind.internship.mini.project.common.exceptions;

public class RoomTooSmallException extends RuntimeException{
    public RoomTooSmallException(Long id){
        super("Room with id: "+id+" is too small for this reservation");
    }
}
