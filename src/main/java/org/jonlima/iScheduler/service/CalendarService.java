//package org.jonlima.iScheduler.service;
//
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.gson.GsonFactory;
//
//import com.google.api.services.calendar.Calendar;
//import com.google.api.services.calendar.CalendarScopes;
//import com.google.api.services.calendar.model.Event;
//import com.google.api.services.calendar.model.EventDateTime;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Collections;
//import java.util.Date;
//
//@Service
//public class CalendarService {
//
//    private static final String APPLICATION_NAME = "YourApplicationName";
//    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
//    private static final String CREDENTIALS_FILE_PATH = "/path/to/your/credentials.json";
//    private static final String CALENDAR_ID = "your-calendar-id@group.calendar.google.com";
//
//    private Calendar calendarService;
//
//    public CalendarService() throws IOException, GeneralSecurityException {
//        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//        this.calendarService = new Calendar.Builder(httpTransport, JSON_FACTORY, null)
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//    }
//
//    public void createEvent(String eventName, String eventDescription, LocalDateTime eventStart, LocalDateTime eventEnd, String location) throws IOException {
//        Event event = new Event()
//                .setSummary(eventName)
//                .setDescription(eventDescription)
//                .setLocation(location);
//
//        Date startDate = Date.from(eventStart.atZone(ZoneId.systemDefault()).toInstant());
//        Date endDate = Date.from(eventEnd.atZone(ZoneId.systemDefault()).toInstant());
//
//        EventDateTime start = new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(startDate)).setTimeZone("YourTimeZone");
//        EventDateTime end = new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(endDate)).setTimeZone("YourTimeZone");
//
//        event.setStart(start);
//        event.setEnd(end);
//
//        this.calendarService.events().insert(CALENDAR_ID, event).execute();
//    }
//}
//
