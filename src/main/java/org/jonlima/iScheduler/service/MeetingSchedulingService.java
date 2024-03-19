package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.TimeBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class MeetingSchedulingService {

    private final AvailabilityService availabilityService;

    @Autowired
    public MeetingSchedulingService(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    public TimeBlock findEarliestMeetingTimeSlot(Long userId1, Long userId2, int meetingDuration) {
        // Retrieve the availabilities of both users from the database
        Availability availability1 = availabilityService.findByUserId(userId1);
        Availability availability2 = availabilityService.findByUserId(userId2);

        // Initialize variables to store the earliest overlapping time slot found
        TimeBlock earliestOverlap = null;
        boolean isFirstOverlap = true;

        // Iterate through the time blocks of each user's availability
        for (TimeBlock timeBlock1 : availability1.getTimeBlocks()) {
            for (TimeBlock timeBlock2 : availability2.getTimeBlocks()) {
                // Calculate the overlapping time slot
                LocalTime overlapStart = timeBlock1.getStartTime().isAfter(timeBlock2.getStartTime()) ? timeBlock1.getStartTime() : timeBlock2.getStartTime();
                LocalTime overlapEnd = timeBlock1.getEndTime().isBefore(timeBlock2.getEndTime()) ? timeBlock1.getEndTime() : timeBlock2.getEndTime().minusMinutes(meetingDuration);

                // Check if the overlapping time slot is valid (i.e., not negative duration)
                if (!overlapEnd.isBefore(overlapStart)) {
                    // Check if this is the earliest overlapping time slot found so far
                    if (isFirstOverlap || overlapStart.isBefore(earliestOverlap.getStartTime())) {
                        earliestOverlap = new TimeBlock(overlapStart, overlapEnd);
                        isFirstOverlap = false;
                    }
                }
            }
        }

        return earliestOverlap;
    }
}
