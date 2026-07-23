package de.lhind.internship.mini.project.common.exceptions;

public class RoomNotFoundException extends RuntimeException{
    public RoomNotFoundException(Long id){
        super("Room with id: "+id+" is not found");
    }
}
