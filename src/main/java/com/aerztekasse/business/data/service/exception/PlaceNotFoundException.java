package com.aerztekasse.business.data.service.exception;

public class PlaceNotFoundException extends RuntimeException {

    public PlaceNotFoundException(long id) {
        super("Place with id " + id + " not found");
    }

}
