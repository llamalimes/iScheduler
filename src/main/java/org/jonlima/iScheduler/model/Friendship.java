package org.jonlima.iScheduler.model;
import java.util.Objects;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user1;

    @ManyToOne
    private User user2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendship that = (Friendship) o;
        return Objects.equals(user1, that.user1) &&
                Objects.equals(user2, that.user2);
    }

    @Override
    public int hashCode(){
        return Objects.hash(user1, user2);
    }
}
