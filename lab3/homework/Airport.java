import java.util.ArrayList;
import java.util.List;

class Airport {
    private String name;
    private List<Integer> runways;

    public Airport(String name, int numRunways) {
        this.name = name;
        this.runways = new ArrayList<>();
        for (int i = 1; i <= numRunways; i++) {
            runways.add(i);
        }
    }

    public String getName() {
        return name;
    }

    public List<Integer> getRunways() {
        return runways;
    }
}