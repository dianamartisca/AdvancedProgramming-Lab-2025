package org.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/client")
public class ApiClientController {

    private final RestTemplate restTemplate;
    private final String apiBaseUrl;

    public ApiClientController(
            RestTemplate restTemplate,
            @Value("${api.base.url:http://localhost:8081}") String apiBaseUrl) {
        this.restTemplate = restTemplate;
        this.apiBaseUrl = apiBaseUrl;
    }

    @GetMapping("/cities")
    public ResponseEntity<?> getCities() {
        return restTemplate.getForEntity(apiBaseUrl + "/cities", Object.class);
    }

    @GetMapping("/countries")
    public ResponseEntity<?> getCountries() {
        return restTemplate.getForEntity(apiBaseUrl + "/countries", Object.class);
    }

    @PostMapping("/cities")
    public ResponseEntity<?> addCity(@RequestBody Object city) {
        return restTemplate.postForEntity(apiBaseUrl + "/cities", city, Object.class);
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<?> updateCityName(@PathVariable int id, @RequestBody Object city) {
        return restTemplate.exchange(apiBaseUrl + "/cities/" + id,
                                     org.springframework.http.HttpMethod.PUT,
                                     new org.springframework.http.HttpEntity<>(city),
                                     Object.class);
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable int id) {
        return restTemplate.exchange(apiBaseUrl + "/cities/" + id,
                                     org.springframework.http.HttpMethod.DELETE,
                                     null,
                                     Object.class);
    }
}