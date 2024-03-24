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
    private Users users1;

    @ManyToOne
    private Users users2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendship that = (Friendship) o;
        return Objects.equals(users1, that.users1) &&
                Objects.equals(users2, that.users2);
    }

    @Override
    public int hashCode(){
        return Objects.hash(users1, users2);
    }
}
