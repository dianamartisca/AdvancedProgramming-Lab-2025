package org.example.repositories;

import org.example.entities.City;

public class CityRepository extends AbstractRepository<City, Integer> {
    public CityRepository() {
        super(City.class);
    }
}