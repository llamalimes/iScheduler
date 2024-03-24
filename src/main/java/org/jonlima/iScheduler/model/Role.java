package org.jonlima.iScheduler.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(nullable = false, unique = true)
    String name;
    @ManyToMany(mappedBy = "roles")
    List<Users> users = new ArrayList<>();

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name='" + name + "'}";
    }
}
