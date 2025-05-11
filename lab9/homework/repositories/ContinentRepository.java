package org.example.repositories;

import org.example.entities.Continent;

public class ContinentRepository extends AbstractRepository<Continent, Integer> {
    public ContinentRepository() {
        super(Continent.class);
    }
}