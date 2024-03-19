package org.jonlima.iScheduler.service.impl;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.TimeBlock;
import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.model.dto.AvailabilityForm;
import org.jonlima.iScheduler.model.dto.TimeBlockForm;
import org.jonlima.iScheduler.repository.AvailabilityRepository;
import org.jonlima.iScheduler.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    @Autowired
    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public void saveAvailability(Availability availability) {
        availabilityRepository.save(availability);
    }

    @Override
    public List<Availability> findAvailabilitiesByUser(User user) {
        return availabilityRepository.findByUser(user);
    }

    @Override
    public List<Availability> findAvailabilitiesByUserAndDayOfWeek(User user, DayOfWeek dayOfWeek) {
        return availabilityRepository.findByUserAndDayOfWeek(user, dayOfWeek);
    }
    @Override
    public Map<DayOfWeek, List<Availability>> findAvailabilitiesGroupedByDay(User user) {
        List<Availability> availabilities = findAvailabilitiesByUser(user);
        return availabilities.stream()
                .collect(Collectors.groupingBy(Availability::getDayOfWeek));
    }
    @Override
    public void initializeClosedAvailability(User user) {
        Availability availability = new Availability();
        availability.setUser(user);
        availabilityRepository.save(availability);
    }

    @Override
    public void deleteAvailability(Availability availability) {
        availabilityRepository.delete(availability);
    }

    // Helper method to convert AvailabilityForm to Availability entity
   @Override
    public Availability convertToAvailability(AvailabilityForm availabilityForm, User user) {
        Availability availability = new Availability();

        // Set the user for the availability
        availability.setUser(user);

        // Set other fields based on the form data
        availability.setDayOfWeek(availabilityForm.getDayOfWeek());

        // Convert TimeBlockForm objects to TimeBlock entities
        List<TimeBlock> timeBlocks = availabilityForm.getTimeBlocks().stream()
                .map(this::convertToTimeBlock)
                .collect(Collectors.toList());

        // Set the time blocks for the availability
        availability.setTimeBlocks(timeBlocks);

        return availability;
    }

    @Override
    public List<TimeBlock> findOverlappingTimeBlocks(List<Availability> userAvailabilities, List<Availability> friendAvailabilities, int meetingDuration) {
        List<TimeBlock> overlappingTimeBlocks = new ArrayList<>();

        for (Availability userAvailability : userAvailabilities) {
            for (Availability friendAvailability : friendAvailabilities) {
                if (userAvailability.getDayOfWeek() == friendAvailability.getDayOfWeek()) {
                    List<TimeBlock> userTimeBlocks = userAvailability.getTimeBlocks();
                    List<TimeBlock> friendTimeBlocks = friendAvailability.getTimeBlocks();

                    for (TimeBlock userTimeBlock : userTimeBlocks) {
                        for (TimeBlock friendTimeBlock : friendTimeBlocks) {
                            // Check if the time blocks overlap
                            if (isOverlapping(userTimeBlock, friendTimeBlock)) {
                                // Check if the overlapping duration is enough for the meeting
                                if (calculateOverlapDuration(userTimeBlock, friendTimeBlock).toMinutes() >= meetingDuration) {
                                    overlappingTimeBlocks.add(getOverlapTimeBlock(userTimeBlock, friendTimeBlock, meetingDuration));
                                }
                            }
                        }
                    }
                }
            }
        }

        return overlappingTimeBlocks;
    }
    // Helper method to check if two time blocks overlap
    private boolean isOverlapping(TimeBlock timeBlock1, TimeBlock timeBlock2) {
        return !timeBlock1.getEndTime().isBefore(timeBlock2.getStartTime()) && !timeBlock2.getEndTime().isBefore(timeBlock1.getStartTime());
    }
    // Helper method to calculate the overlap duration between two time blocks
    private Duration calculateOverlapDuration(TimeBlock timeBlock1, TimeBlock timeBlock2) {
        return Duration.between(
                timeBlock1.getStartTime().isAfter(timeBlock2.getStartTime()) ? timeBlock1.getStartTime() : timeBlock2.getStartTime(),
                timeBlock1.getEndTime().isBefore(timeBlock2.getEndTime()) ? timeBlock1.getEndTime() : timeBlock2.getEndTime()
        );
    }
    // Helper method to create a TimeBlock representing the overlapping time slot with the meeting duration
    private TimeBlock getOverlapTimeBlock(TimeBlock timeBlock1, TimeBlock timeBlock2, int meetingDuration) {
        return new TimeBlock(
                timeBlock1.getStartTime().isAfter(timeBlock2.getStartTime()) ? timeBlock1.getStartTime() : timeBlock2.getStartTime(),
                timeBlock1.getEndTime().isBefore(timeBlock2.getEndTime()) ? timeBlock1.getEndTime() : timeBlock2.getEndTime().minusMinutes(meetingDuration)
        );
    }
    @Override
    public TimeBlock findEarliestTimeBlock(List<TimeBlock> timeBlocks) {
        if (timeBlocks.isEmpty()) {
            return null;
        }

        // Sort the time blocks by start time
        timeBlocks.sort((tb1, tb2) -> tb1.getStartTime().compareTo(tb2.getStartTime()));

        // Return the earliest time block
        return timeBlocks.get(0);
    }

    // Helper method to convert TimeBlockForm to TimeBlock
    private TimeBlock convertToTimeBlock(TimeBlockForm timeBlockForm) {
        TimeBlock timeBlock = new TimeBlock();
        timeBlock.setStartTime(timeBlockForm.getStartTime());
        timeBlock.setEndTime(timeBlockForm.getEndTime());
        return timeBlock;
    }


}