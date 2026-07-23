package de.lhind.internship.mini.project.common.exceptions;

public class AddressAlreadyOccupiedException extends RuntimeException{
    public AddressAlreadyOccupiedException(String address){
        super("This address: "+address+" already has a building or a hotel");
    }
}
