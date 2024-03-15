package org.jonlima.iScheduler.model;

import jakarta.persistence.*;
import lombok.Data;

import com.google.api.client.util.DateTime;

import java.util.List;
@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private String location;
    private DateTime startDateTime;
    private DateTime endDateTime;

    @ManyToOne
    private User creator;

    @ManyToMany
    private List<User> participants;

    @ManyToOne
    private Calendar calendar;
}
