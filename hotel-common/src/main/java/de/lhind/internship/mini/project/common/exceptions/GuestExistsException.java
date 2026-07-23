package de.lhind.internship.mini.project.common.exceptions;

public class GuestExistsException extends RuntimeException{
    public GuestExistsException(String email){
        super("Guest with email: "+email+" already exists");
    }

    public GuestExistsException(Long id){
        super("Guest with id: "+id+" already exists");
    }
}
