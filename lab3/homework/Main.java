import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Airport airport = new Airport("Air France", 3);
        SchedulingProblem problem = new SchedulingProblem(airport);

        Airliner airliner1 = new Airliner("Boeing 747", "123", "SkyKing", 400, 64.84);
        Airliner airliner2 = new Airliner("Airbus A320", "456", "CloudsKing", 230, 35.98);
        Freighter freighter1 = new Freighter("Boeing 777F", "CARGO1", "Hulk", 100000);
        Freighter freighter2 = new Freighter("Antonov", "CARGO2", "SuperStrength", 250000);

        problem.addFlight(new Flight("01", airliner1, LocalTime.of(10, 0), LocalTime.of(10, 30)));
        problem.addFlight(new Flight("02", airliner2, LocalTime.of(10, 15), LocalTime.of(10, 45)));
        problem.addFlight(new Flight("03", airliner1, LocalTime.of(16, 20), LocalTime.of(16, 40)));
        problem.addFlight(new Flight("04", freighter1, LocalTime.of(16, 15), LocalTime.of(16, 45)));
        problem.addFlight(new Flight("05", freighter2, LocalTime.of(18, 10), LocalTime.of(18, 30)));
        problem.addFlight(new Flight("06", freighter1, LocalTime.of(18, 15), LocalTime.of(18, 45)));

        problem.scheduleFlights();
        problem.printSchedule();
    }
}