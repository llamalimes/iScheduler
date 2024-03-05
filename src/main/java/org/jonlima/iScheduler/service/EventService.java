package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.Event;
import org.jonlima.iScheduler.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    //CREATE
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    //READ
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(long id){
        return eventRepository.findById(id);
    }

    //UPDATE
    public Event updateEvent(Long id, Event event){
        Optional<Event> existingEventOptional = getEventById(id);
        if(existingEventOptional.isPresent()){
            Event existingEvent = existingEventOptional.get();
            existingEvent.setTitle(event.getTitle());
            existingEvent.setDescription(event.getDescription());
            existingEvent.setLocation(event.getLocation());
            existingEvent.setStartDateTime(event.getStartDateTime());
            existingEvent.setEndDateTime(event.getEndDateTime());
            existingEvent.setCreator(event.getCreator());
            existingEvent.setParticipants(event.getParticipants());
            existingEvent.setCalendar(event.getCalendar());
            return eventRepository.save(existingEvent);
        } else {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
    }

    //DELETE
    public void deleteEvent(long id){
        eventRepository.deleteById(id);
    }
}
