package org.jonlima.iScheduler.model;

import jakarta.persistence.*;
import lombok.Data;
import org.jonlima.iScheduler.exceptions.AvailabilityValidationException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private DayOfWeek dayOfWeek;

    @ElementCollection
    @CollectionTable(name = "availability_time_blocks", joinColumns = @JoinColumn(name = "availability_id"))
    private List<TimeBlock> timeBlocks = new ArrayList<>();

    // Method to validate time blocks
    public void validateTimeBlocks() throws AvailabilityValidationException {
        // Validate each time block
        for (int i = 0; i < timeBlocks.size(); i++) {
            TimeBlock currentBlock = timeBlocks.get(i);
            LocalTime currentStart = currentBlock.getStartTime();
            LocalTime currentEnd = currentBlock.getEndTime();

            // Check if start time is before end time
            if (currentStart.isAfter(currentEnd)) {
                throw new AvailabilityValidationException("Start time must be before end time");
            }

            // Check for overlapping time blocks
            for (int j = i + 1; j < timeBlocks.size(); j++) {
                TimeBlock otherBlock = timeBlocks.get(j);
                LocalTime otherStart = otherBlock.getStartTime();
                LocalTime otherEnd = otherBlock.getEndTime();

                // Check for overlap
                if (!(currentEnd.isBefore(otherStart) || currentStart.isAfter(otherEnd))) {
                    throw new AvailabilityValidationException("Time blocks cannot overlap");
                }
            }
        }
    }
}
