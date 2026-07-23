package de.lhind.internship.mini.project.common.exceptions;

public class RoomNotAvailableException extends RuntimeException{
    public RoomNotAvailableException(Long id){
        super("Room with id: "+id+" is not available");
    }
}
