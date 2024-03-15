package org.jonlima.iScheduler.dto;

import lombok.Data;

import com.google.api.client.util.DateTime;


@Data
public class EventDTO {
    private String title;
    private String description;
    private DateTime startDateTime;
    private DateTime endDateTime;
    private String location;
}
