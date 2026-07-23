package de.lhind.internship.mini.project.common.exceptions;

public class GuestNotFoundException extends RuntimeException{
    public GuestNotFoundException(Long id){
        super("Guest with id: "+id+" doesn't exist");
    }
}
