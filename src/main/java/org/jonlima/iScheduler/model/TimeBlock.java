package org.jonlima.iScheduler.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalTime;

@Embeddable
@Data
public class TimeBlock {

    private LocalTime startTime;

    private LocalTime endTime;

    // Constructor with startTime and endTime arguments
    public TimeBlock(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Default constructor (required for Hibernate)
    public TimeBlock() {
    }

}
