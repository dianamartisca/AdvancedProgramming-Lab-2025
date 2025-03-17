import java.util.HashSet;
import java.util.Set;

class SchedulingProblem {
    private Airport airport;
    private Set<Flight> flights;

    public SchedulingProblem(Airport airport) {
        this.airport = airport;
        this.flights = new HashSet<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void scheduleFlights() {
        for (Flight flight : flights) {
            for (int runway : airport.getRunways()) {
                boolean conflict = false;
                for (Flight otherFlight : flights) {
                    if (otherFlight.getRunway() == runway && flight.conflictsWith(otherFlight)) {
                        conflict = true;
                        break;
                    }
                }
                if (!conflict) {
                    flight.setRunway(runway);
                    break;
                }
            }
        }
    }

    public void printSchedule() {
        System.out.println("Flight schedule for airport " + airport.getName() + ":");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
}
