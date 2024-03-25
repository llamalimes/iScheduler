package org.jonlima.iScheduler.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Embeddable
@Data
public class TimeBlock {

    private LocalTime startTime;

    private LocalTime endTime;
    private DayOfWeek dayOfWeek;
    public TimeBlock(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        dayOfWeek = null;
    }
    public TimeBlock() {
    }
    public boolean overlapsWith(TimeBlock other) {
        return !this.endTime.isBefore(other.startTime) && !this.startTime.isAfter(other.endTime);
    }

    public TimeBlock getOverlap(TimeBlock other) {
        LocalTime maxStart = this.startTime.isAfter(other.startTime) ? this.startTime : other.startTime;
        LocalTime minEnd = this.endTime.isBefore(other.endTime) ? this.endTime : other.endTime;

        if (!maxStart.plusHours(1).isAfter(minEnd)) {
            return new TimeBlock(maxStart, maxStart.plusHours(1));
        }

        return null;
    }
}

