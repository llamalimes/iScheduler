package org.jonlima.iScheduler.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany
    private List<User> members;

    @OneToOne
    private Calendar calendar;
}
