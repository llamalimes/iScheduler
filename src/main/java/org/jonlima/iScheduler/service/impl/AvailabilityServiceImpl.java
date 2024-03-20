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
    public TimeBlock findCommonAvailability(User user1, User user2) {
        List<Availability> availabilities1 = user1.getAvailabilities();
        List<Availability> availabilities2 = user2.getAvailabilities();

        for (int day = 0; day < 7; day++) { // Assuming 0 = Monday, 1 = Tuesday, ..., 6 = Sunday
            List<TimeBlock> blocks1 = availabilities1.get(day).getTimeBlocks();
            List<TimeBlock> blocks2 = availabilities2.get(day).getTimeBlocks();

            for (TimeBlock block1 : blocks1) {
                for (TimeBlock block2 : blocks2) {
                    if (block1.overlapsWith(block2)) {
                        TimeBlock overlap = block1.getOverlap(block2);
                        if (overlap != null) {
                            return overlap;
                        }
                    }
                }
            }
        }

        return null; // No common 1-hour availability found
    }





    // Helper method to convert TimeBlockForm to TimeBlock
    private TimeBlock convertToTimeBlock(TimeBlockForm timeBlockForm) {
        TimeBlock timeBlock = new TimeBlock();
        timeBlock.setStartTime(timeBlockForm.getStartTime());
        timeBlock.setEndTime(timeBlockForm.getEndTime());
        return timeBlock;
    }

}