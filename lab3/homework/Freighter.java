/**
 * Represents a Freighter that carries cargo.
 */
class Freighter extends Aircraft implements CargoCapable {
    private double maxPayload;

    public Freighter(String model, String tailNumber, String name, double maxPayload) {
        super(model, tailNumber, name);
        this.maxPayload = maxPayload;
    }

    @Override
    public double getMaxPayload() {
        return maxPayload;
    }

    @Override
    public String toString() {
        return super.toString() + ", max payload: " + maxPayload + "kg.";
    }
}