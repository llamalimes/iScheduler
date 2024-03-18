package org.jonlima.iScheduler.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private DayOfWeek dayOfWeek;

    @ElementCollection
    @CollectionTable(name = "availability_time_blocks", joinColumns = @JoinColumn(name = "availability_id"))
    private List<TimeBlock> timeBlocks = new ArrayList<>();

}

@Embeddable
@Data
class TimeBlock {

    private LocalTime startTime;

    private LocalTime endTime;


}
