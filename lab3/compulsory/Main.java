/**
 * Main class to test the aircraft system.
 */
public class Main {
    public static void main(String[] args) {
        Airliner airliner1 = new Airliner("Boeing 747", "123", "SkyKing", 400, 64.84);
        Airliner airliner2 = new Airliner("Airbus A320", "456", "CloudsKing", 230, 35.98);
        Freighter freighter1 = new Freighter("Boeing 777F", "CARGO1", "Hulk", 100000);
        Freighter freighter2 = new Freighter("Antonov", "CARGO2", "SuperStrength", 250000);
        Drone drone1 = new Drone("DJI Matrice", "DRONE1", "DeliverGuy", 190, 10);
        Drone drone2 = new Drone("Wingcopter", "DRONE2", "TheCamera", 120);

        Aircraft[] allAircraft = {airliner1, airliner2, freighter1, freighter2, drone1, drone2};
        Aircraft[] cargoAircraft = getCargoCapable(allAircraft);
        Aircraft[] passengerAircraft = getPassengerCapable(allAircraft);

        System.out.println("Aircraft that can transport goods:");
        for (Aircraft aircraft : cargoAircraft) {
            System.out.println(aircraft);
        }

        System.out.println();
        System.out.println("Aircraft that can transport passengers:");
        for (Aircraft aircraft : passengerAircraft) {
            System.out.println(aircraft);
        }
    }

    /**
     * Filters the aircraft that can transport cargo.
     *
     * @param aircraft all aircraft
     * @return cargoAircraft array of aircraft carrying goods
     */
    public static Aircraft[] getCargoCapable(Aircraft[] aircraft) {
        int number = 0;
        for (Aircraft a : aircraft) {
            if (a instanceof CargoCapable || (a instanceof Drone && ((Drone) a).getMaxPayload() != null)) {
                number++;
            }
        }
        Aircraft[] cargoAircraft = new Aircraft[number];
        int index = 0;
        for (Aircraft a : aircraft) {
            if (a instanceof CargoCapable || (a instanceof Drone && ((Drone) a).getMaxPayload() != null)) {
                cargoAircraft[index++] = a;
            }
        }
        return cargoAircraft;
    }

    /**
     * Filters the aircraft that can transport passengers.
     *
     * @param aircraft all aircraft
     * @return passengerAircraft array of aircraft carrying passengers
     */
    public static Aircraft[] getPassengerCapable(Aircraft[] aircraft) {
        int number = 0;
        for (Aircraft a : aircraft) {
            if (a instanceof PassengerCapable) {
                number++;
            }
        }
        Aircraft[] passengerAircraft = new Aircraft[number];
        int index = 0;
        for (Aircraft a : aircraft) {
            if (a instanceof PassengerCapable) {
                passengerAircraft[index++] = a;
            }
        }
        return passengerAircraft;
    }
}