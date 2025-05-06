package org.example;

import org.example.entities.Continent;
import org.example.entities.Country;
import org.example.repositories.ContinentRepository;
import org.example.repositories.CountryRepository;

public class Main {
    public static void main(String[] args) {
        ContinentRepository continentRepository = new ContinentRepository();
        CountryRepository countryRepository = new CountryRepository();

        Continent continent = new Continent();
        continent.setName("Asia");
        continentRepository.create(continent);
        Continent continent2 = new Continent();
        continent2.setName("America");
        continentRepository.create(continent2);

        Continent foundContinent = continentRepository.findById(1);
        System.out.println("Found Continent by Id: " + foundContinent.getName());
        System.out.println("Found Continents by name:");
        continentRepository.findByName("A").forEach(c -> System.out.println(c.getName()));

        Country country = new Country();
        country.setName("China");
        country.setCode("CH");
        country.setContinent(continent);
        countryRepository.create(country);

        Country foundCountry = countryRepository.findById(1);
        System.out.println("Found Country by Id: " + foundCountry.getName());
        System.out.println("Found Countries by name:");
        countryRepository.findByName("Chi").forEach(c -> System.out.println(c.getName()));
    }
}