package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.entities.Country;
import org.example.repositories.CountryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@Tag(name= "Country API", description = "Operations related to countries")
public class CountryController {
    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Operation(summary = "Get all countries", description = "Returns a list of all countries")
    @GetMapping
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}