package org.jonlima.iScheduler.model.dto;

import java.time.DayOfWeek;
import java.util.List;

public class AvailabilityForm {

    private DayOfWeek dayOfWeek;

    private List<TimeBlockForm> timeBlocks;

    // Getters and setters

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<TimeBlockForm> getTimeBlocks() {
        return timeBlocks;
    }

    public void setTimeBlocks(List<TimeBlockForm> timeBlocks) {
        this.timeBlocks = timeBlocks;
    }
}
