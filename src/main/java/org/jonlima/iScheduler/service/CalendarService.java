package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.Calendar;
import org.jonlima.iScheduler.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    @Autowired
    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    //CREATE
    public Calendar createCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }
    //READ
    public List<Calendar> getAllCalendars() {
        return calendarRepository.findAll();
    }

    public Optional<Calendar> getCalendarById(Long id) {
        return calendarRepository.findById(id);
    }
    //UPDATE
    public Calendar updateCalendar(long id, Calendar calendar) {
        Optional<Calendar> existingCalendarOptional = calendarRepository.findById(id);
        if (existingCalendarOptional.isPresent()) {
            Calendar existingCalendar = existingCalendarOptional.get();
            existingCalendar.setUser(calendar.getUser());
            existingCalendar.setEvents(calendar.getEvents());
            return calendarRepository.save(existingCalendar);
        } else {
            throw new IllegalArgumentException("Calendar not found with id: " + id);
        }
    }
    //DELETE
    public void deleteCalendar(Long id) {
        calendarRepository.deleteById(id);
    }
}
