package de.lhind.internship.mini.project.common.exceptions;

public class GuestHasProfileException extends RuntimeException{
    public GuestHasProfileException(Long id){
        super("Guest with id: "+id+" already has a guest profile, we can't add another one");
    }
}
