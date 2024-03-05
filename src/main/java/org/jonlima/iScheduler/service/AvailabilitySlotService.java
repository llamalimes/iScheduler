package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.AvailabilitySlot;
import org.jonlima.iScheduler.repository.AvailabilitySlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilitySlotService {

    private final AvailabilitySlotRepository availabilitySlotRepository;

    @Autowired
    public AvailabilitySlotService(AvailabilitySlotRepository availabilitySlotRepository){
        this.availabilitySlotRepository = availabilitySlotRepository;
    }

    //CREATE
    public AvailabilitySlot createAvailabilitySlot(AvailabilitySlot availabilitySlot) {
        return availabilitySlotRepository.save(availabilitySlot);
    }

    //READ
    public List<AvailabilitySlot> getAllAvailabilitySlots() {
        return availabilitySlotRepository.findAll();
    }

    public Optional<AvailabilitySlot> getAvailabilitySlotById(long id) {
        return availabilitySlotRepository.findById(id);
    }

    //UPDATE
    public AvailabilitySlot updateAvailabilitySlot(long id, AvailabilitySlot availabilitySlot) {
        Optional<AvailabilitySlot> existingSlotOptional = availabilitySlotRepository.findById(id);
        if (existingSlotOptional.isPresent()) {
            AvailabilitySlot existingSlot = existingSlotOptional.get();
            existingSlot.setUser(availabilitySlot.getUser());
            existingSlot.setStartDateTime(availabilitySlot.getStartDateTime());
            existingSlot.setEndDateTime(availabilitySlot.getEndDateTime());
            return availabilitySlotRepository.save(existingSlot);
        } else {
            throw new IllegalArgumentException("Availability slot not found with id: " + id);
        }
    }

    //DELETE
    public void deleteAvailabilitySlot(Long id) {
        availabilitySlotRepository.deleteById(id);
    }
}
