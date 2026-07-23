package de.lhind.internship.mini.project.common.exceptions;

public class HotelNotFoundException extends RuntimeException{
    public HotelNotFoundException(Long id){
        super("Hotel with id: "+id+" doesn't exist");
    }

    public HotelNotFoundException(String address){
        super("Hotel with address: "+address+" doesn't exist");
    }
}
