package de.lhind.internship.mini.project.common.exceptions;

public class GuestProfileNotFoundException extends RuntimeException{
    public GuestProfileNotFoundException(){
        super("Guest Profile is not found");
    }
//
//    public GuestProfileNotFoundException(Long guestId){
//        super("Guest with id: "+guestId+" has no guestProfile");
//    }
}
