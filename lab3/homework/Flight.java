import java.time.LocalTime;

class Flight {
    private String flightId;
    private Aircraft aircraft;
    private LocalTime arrivalStart;
    private LocalTime arrivalEnd;
    private int assignedRunway = -1;

    public Flight(String flightId, Aircraft aircraft, LocalTime arrivalStart, LocalTime arrivalEnd) {
        this.flightId = flightId;
        this.aircraft = aircraft;
        this.arrivalStart = arrivalStart;
        this.arrivalEnd = arrivalEnd;
    }

    public void setRunway(int runway) {
        this.assignedRunway = runway;
    }

    public String getFlightId() {
        return flightId;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public LocalTime getArrivalStart() {
        return arrivalStart;
    }

    public LocalTime getArrivalEnd() {
        return arrivalEnd;
    }

    public int getRunway() {
        return assignedRunway;
    }

    public boolean conflictsWith(Flight other) {
        return !(this.arrivalEnd.isBefore(other.arrivalStart) || this.arrivalStart.isAfter(other.arrivalEnd));
    }

    @Override
    public String toString() {
        return "Flight " + flightId + " of aircraft " + aircraft.getName() + " is landing between " + arrivalStart + " and " + arrivalEnd + " on runway " + (assignedRunway == -1 ? "not available" : assignedRunway + ".");
    }
}
