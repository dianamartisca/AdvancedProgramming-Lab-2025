package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.entities.City;
import org.example.repositories.CityRepository;
import org.example.repositories.CountryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
@Tag(name = "City API", description = "Operations related to cities")
public class CityController {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public CityController(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Operation(summary = "Get all cities", description = "Returns a list of all cities")
    @GetMapping
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Operation(summary = "Create a city", description = "Creates a new city with the specified details")
    @PostMapping
    public ResponseEntity<?> addCity(@RequestBody City city) {
        if (!countryRepository.existsById(city.getCountryId())) {
            return ResponseEntity.badRequest().body("Country with ID " + city.getCountryId() + " does not exist.");
        }
        City savedCity = cityRepository.save(city);
        return ResponseEntity.ok(savedCity);
    }

    @Operation(summary = "Update a city", description = "Updates the name of the city with the specified ID")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCityName(@PathVariable int id, @RequestBody City cityBody) {
        Optional<City> cityOpt = cityRepository.findById(id);
        if (cityOpt.isEmpty() || cityBody.getName() == null) {
            return ResponseEntity.badRequest().body("City name cannot be null.");
        }
        City city = cityOpt.get();
        city.setName(cityBody.getName());
        cityRepository.save(city);
        return ResponseEntity.ok(city);
    }

    @Operation(summary = "Delete a city", description = "Deletes the city with the specified ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable int id) {
        if (!cityRepository.existsById(id)) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND)
                    .body("City with ID " + id + " does not exist.");
        }
        cityRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}