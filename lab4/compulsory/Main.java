package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Location[] locations = {
                new Location("Postal Office", Location.Type.friendly),
                new Location("Hotel Agora", Location.Type.enemy),
                new Location("Train Station", Location.Type.neutral),
                new Location("High School", Location.Type.friendly),
                new Location("Fire Station", Location.Type.enemy),
                new Location("Park", Location.Type.friendly)
        };

        TreeSet<Location> friendlyLocations = Arrays.stream(locations)
                .filter(loc -> loc.getType() == Location.Type.friendly)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println("Friendly locations:");
        friendlyLocations.forEach(System.out::println);

        LinkedList<Location> enemyLocations = Arrays.stream(locations)
                .filter(loc -> loc.getType() == Location.Type.enemy)
                .sorted(Comparator.comparing(Location::getType).thenComparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println("\nEnemy Locations:");
        enemyLocations.forEach(System.out::println);
    }
}
