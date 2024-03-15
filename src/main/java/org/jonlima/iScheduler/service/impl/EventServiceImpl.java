package org.jonlima.iScheduler.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.EventDateTime;
import org.jonlima.iScheduler.dto.EventDTO;
import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(EventDTO eventDTO, User user, String credentialsPath) throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        Calendar service = new Calendar.Builder(httpTransport, jsonFactory, (HttpRequestInitializer) GoogleCredentials.fromStream(new FileInputStream(credentialsPath))
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/calendar")))
                .setApplicationName("iScheduler")
                .build();

        com.google.api.services.calendar.model.Event googleEvent = new com.google.api.services.calendar.model.Event()
                .setSummary(eventDTO.getTitle())
                .setDescription(eventDTO.getDescription());

        EventDateTime startDateTime = new EventDateTime()
                .setDateTime(eventDTO.getStartDateTime())
                .setTimeZone("Your_TimeZone");
        googleEvent.setStart(startDateTime);

        EventDateTime endDateTime = new EventDateTime()
                .setDateTime(eventDTO.getEndDateTime())
                .setTimeZone("Your_TimeZone");
        googleEvent.setEnd(endDateTime);

        service.events().insert("primary", googleEvent).execute();

        org.jonlima.iScheduler.model.Event event = new org.jonlima.iScheduler.model.Event();
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setStartDateTime(eventDTO.getStartDateTime());
        event.setEndDateTime(eventDTO.getEndDateTime());
        event.setLocation(eventDTO.getLocation());

        eventRepository.save(event);
    }

    // You can implement more methods here as needed
}
