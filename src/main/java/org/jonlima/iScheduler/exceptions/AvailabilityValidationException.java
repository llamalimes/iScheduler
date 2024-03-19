package org.jonlima.iScheduler.exceptions;

import org.jonlima.iScheduler.model.Availability;

public class AvailabilityValidationException  extends RuntimeException{
    public AvailabilityValidationException(String message){
        super(message);
    }
}
