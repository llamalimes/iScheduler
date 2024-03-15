package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.dto.EventDTO;
import org.jonlima.iScheduler.model.User;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface EventService {
    void createEvent(EventDTO eventDTO, User user, String credentialsPath) throws IOException, GeneralSecurityException;
}
