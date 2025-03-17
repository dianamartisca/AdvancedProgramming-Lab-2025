/**
 * Represents an Airliner that carries passengers.
 */
class Airliner extends Aircraft implements PassengerCapable {
    private int passengerCapacity;
    private double wingSpan;

    public Airliner(String model, String tailNumber, String name, int passengerCapacity, double wingSpan) {
        super(model, tailNumber, name);
        this.passengerCapacity = passengerCapacity;
        this.wingSpan = wingSpan;
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public double getWingSpan() {
        return wingSpan;
    }

    @Override
    public String toString() {
        return super.toString() + ", number of passengers: " + passengerCapacity + ", wing span: " + wingSpan + "metres.";
    }
}