package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "continents")
@NamedQuery(
        name = "Continent.findByName",
        query = "select c from Continent c where c.name like :namePattern"
)
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}