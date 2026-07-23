package de.lhind.internship.mini.project.common.exceptions;

public class HotelAlreadyExistsException extends RuntimeException{
    public HotelAlreadyExistsException(Long id){
        super("There is already a hotel with id: "+id);
    }

    public HotelAlreadyExistsException(String address){
        super("There is a hotel in this address");
    }
}
