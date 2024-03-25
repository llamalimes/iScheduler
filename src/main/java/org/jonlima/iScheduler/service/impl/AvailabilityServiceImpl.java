package org.jonlima.iScheduler.service.impl;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.TimeBlock;
import org.jonlima.iScheduler.model.Users;
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
    public List<Availability> findAvailabilitiesByUser(Users users) {
        return availabilityRepository.findByUsers(users);
    }

    @Override
    public List<Availability> findAvailabilitiesByUserAndDayOfWeek(Users users, DayOfWeek dayOfWeek) {
        return availabilityRepository.findByUsersAndDayOfWeek(users, dayOfWeek);
    }
    @Override
    public Map<DayOfWeek, List<Availability>> findAvailabilitiesGroupedByDay(Users users) {
        List<Availability> availabilities = findAvailabilitiesByUser(users);
        return availabilities.stream()
                .collect(Collectors.groupingBy(Availability::getDayOfWeek));
    }
    @Override
    public void initializeClosedAvailability(Users users) {
        Availability availability = new Availability();
        availability.setUsers(users);
        availabilityRepository.save(availability);
    }

    @Override
    public void deleteAvailability(Availability availability) {
        availabilityRepository.delete(availability);
    }

    // Helper method to convert AvailabilityForm to Availability entity
   @Override
    public Availability convertToAvailability(AvailabilityForm availabilityForm, Users users) {
        Availability availability = new Availability();

        // Set the users for the availability
        availability.setUsers(users);

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
    public TimeBlock findCommonAvailability(Users users1, Users users2) {
        List<Availability> availabilities1 = users1.getAvailabilities();
        List<Availability> availabilities2 = users2.getAvailabilities();

        for (int day = 1; day < 8; day++) { // Assuming 1 = Monday, 2 = Tuesday, ..., 7 = Sunday
            List<TimeBlock> blocks1 = availabilities1.get(day).getTimeBlocks();
            List<TimeBlock> blocks2 = availabilities2.get(day).getTimeBlocks();

            for (TimeBlock block1 : blocks1) {
                for (TimeBlock block2 : blocks2) {
                    if (block1.overlapsWith(block2)) {
                        TimeBlock overlap = block1.getOverlap(block2);
                        if (overlap != null) {
                            overlap.setDayOfWeek(DayOfWeek.of((day+2)%7));
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