package org.jonlima.iScheduler.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalTime;

@Embeddable
@Data
public class TimeBlock {

    private LocalTime startTime;

    private LocalTime endTime;


}
