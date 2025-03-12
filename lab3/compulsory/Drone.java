/**
 * Represents a Drone that can either carry cargo or be used for surveillance.
 */
class Drone extends Aircraft {
    private double batteryLife;
    private Double maxPayload;

    public Drone(String model, String tailNumber, String name, double batteryLife, double maxPayload) {
        super(model, tailNumber, name);
        this.batteryLife = batteryLife;
        this.maxPayload = maxPayload;
    }

    public Drone(String model, String tailNumber, String name, double batteryLife) {
        super(model, tailNumber, name);
        this.batteryLife = batteryLife;
    }

    public Double getMaxPayload() {
        return maxPayload;
    }

    public double getBatteryLife() {
        return batteryLife;
    }

    @Override
    public String toString() {
        String payloadInfo = (maxPayload != null) ? ", max payload: " + maxPayload + "kg." : ".";
        return super.toString() + ", battery life: " + batteryLife + "min" + payloadInfo;
    }
}