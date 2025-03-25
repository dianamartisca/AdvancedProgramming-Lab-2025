package org.example;

import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        Random random = new Random();
        List<Location> locations = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Type type = Type.values()[random.nextInt(3)];
            locations.add(new Location(faker.address().cityName(), type));
        }

        Map<Type, List<Location>> locationsByType = locations.stream()
                .collect(Collectors.groupingBy(Location::getType));

        Graph<Location, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        locations.forEach(graph::addVertex);

        for (int i = 0; i < locations.size(); i++) {
            for (int j = i + 1; j < locations.size(); j++) {
                if (random.nextBoolean()) {
                    int time = 1 + random.nextInt(10);
                    graph.setEdgeWeight(graph.addEdge(locations.get(i), locations.get(j)), time);
                    graph.setEdgeWeight(graph.addEdge(locations.get(j), locations.get(i)), time);
                }
            }
        }

        Location start = locations.get(0);
        System.out.println("Start location: " + start);

        DijkstraShortestPath<Location, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
        Map<Location, Integer> shortestTimes = new HashMap<>();
        shortestTimes.put(start, 0);

        for (Location loc : locations) {
            if (!loc.equals(start)) {
                shortestTimes.put(loc, (int) dijkstra.getPathWeight(start, loc));
            }
        }

        Arrays.stream(Type.values()).forEach(type -> {
            System.out.println("\n" + type + " locations:");
            locationsByType.getOrDefault(type, Collections.emptyList()).forEach(loc ->
                    System.out.println(loc + " - Time: " + shortestTimes.get(loc))
            );
        });
    }
}
