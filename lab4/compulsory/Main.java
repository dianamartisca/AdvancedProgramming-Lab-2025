package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Location[] locations = {
                new Location("Postal Office", Location.Type.FRIENDLY),
                new Location("Hotel Agora", Location.Type.ENEMY),
                new Location("Train Station", Location.Type.NEUTRAL),
                new Location("High School", Location.Type.FRIENDLY),
                new Location("Fire Station", Location.Type.ENEMY),
                new Location("Park", Location.Type.FRIENDLY)
        };

        TreeSet<Location> friendlyLocations = Arrays.stream(locations)
                .filter(loc -> loc.getType() == Location.Type.FRIENDLY)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println("Friendly locations:");
        friendlyLocations.forEach(System.out::println);

        LinkedList<Location> enemyLocations = Arrays.stream(locations)
                .filter(loc -> loc.getType() == Location.Type.ENEMY)
                .sorted(Comparator.comparing(Location::getType).thenComparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println("\nEnemy Locations:");
        enemyLocations.forEach(System.out::println);
    }
}
