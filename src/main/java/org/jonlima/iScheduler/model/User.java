package org.jonlima.iScheduler.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    private String timeZone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Availability> availabilities = new ArrayList<>();

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable (name ="user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id ", referencedColumnName = "id"
            )})
    public List<Role> roles = new ArrayList<>();

    public User() {
        // Create default availabilities
        //createDefaultAvailabilities();
    }
    private void createDefaultAvailabilities() {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            Availability availability = new Availability();
            availability.setUser(this);
            availability.setDayOfWeek(dayOfWeek);

            TimeBlock defaultTimeBlock = new TimeBlock();
            defaultTimeBlock.setStartTime(LocalTime.of(9, 0)); // Example start time
            defaultTimeBlock.setEndTime(LocalTime.of(17, 0)); // Example end time

            availability.getTimeBlocks().add(defaultTimeBlock);

            availabilities.add(availability);
        }
    }
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + "', email='" + email + "', password='" + password + "', timeZone='" + timeZone + "'}";
    }
}
