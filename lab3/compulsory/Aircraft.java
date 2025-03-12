/**
 * Base class representing an aircraft.
 */
public abstract class Aircraft {
    protected String model;
    protected String tailNumber;
    protected String name;

    public Aircraft(String model, String tailNumber, String name) {
        this.model = model;
        this.tailNumber = tailNumber;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + name + ", model: " + model + ", tail number: " + tailNumber;
    }
}










