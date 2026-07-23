package de.lhind.internship.mini.project.common.exceptions;

public class RoomExistsException extends RuntimeException{
    public RoomExistsException(Long id){
        super("Room with id: "+id+" already exists");
    }

    public RoomExistsException(String roomNumber, Long hotelId){
        super("Room with number: "+roomNumber+" at hotel with id: "+hotelId+" already exists");
    }
}
