package org.example.repositories;

import org.example.entities.Country;

public class CountryRepository extends AbstractRepository<Country, Integer> {
    public CountryRepository() {
        super(Country.class);
    }
}