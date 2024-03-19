package org.jonlima.iScheduler.model.dto;

import java.time.LocalTime;

public class TimeBlockForm {

    private LocalTime startTime;

    private LocalTime endTime;

    // Getters and setters

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
