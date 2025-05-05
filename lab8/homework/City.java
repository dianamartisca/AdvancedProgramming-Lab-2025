package org.example;

public class City {
    private int id;
    private String country;
    private String name;
    private boolean capital;
    private double latitude;
    private double longitude;

    public City(int id, String country, String name, boolean capital, double latitude, double longitude) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double calculateDistance(City otherCity) {
        final int R = 6371;
        double latDistance = Math.toRadians(otherCity.latitude - this.latitude);
        double lonDistance = Math.toRadians(otherCity.longitude - this.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(otherCity.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public String getName() {
        return name;
    }
}