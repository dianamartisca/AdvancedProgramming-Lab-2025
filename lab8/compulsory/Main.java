package org.example;

import org.example.dao.Continent;
import org.example.dao.Country;
import org.example.dto.ContinentDTO;
import org.example.dto.CountryDTO;

public class Main {
    public static void main(String[] args) {
        try {
            Continent continentDao = new Continent();
            continentDao.create("Europe");
            ContinentDTO continent = continentDao.findByName("Europe");
            System.out.println(continent);

            Country countryDao = new Country();
            countryDao.create("France", "FR", continent.getId());
            countryDao.create("Romania", "RO", continent.getId());
            CountryDTO country1 = countryDao.findByName("France");
            CountryDTO country2 = countryDao.findById(2);
            System.out.println(country1);
            System.out.println(country2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}