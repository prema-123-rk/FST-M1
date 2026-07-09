package activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Plane {

    // Private fields for encapsulation
    private List<String> passengers;
    private int maxPassengers;
    private Date lastTimeTookOff;
    private Date lastTimeLanded;

    // Constructor
    public Plane(int maxPassengers) {
        this.passengers = new ArrayList<>();
        this.maxPassengers = maxPassengers;
    }

    // Add passenger method
    public boolean addPassenger(String passenger) {
        if (passengers.size() < maxPassengers) {
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    // Take off method
    public void takeOff() {
        lastTimeTookOff = new Date();
        System.out.println("Plane took off at: " + lastTimeTookOff);
    }

    // Landing method
    public void land() {
        lastTimeLanded = new Date();
        System.out.println("Plane landed at: " + lastTimeLanded);
    }

    // Getter methods
    public List<String> getPassengers() {
        return passengers;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public Date getLastTimeTookOff() {
        return lastTimeTookOff;
    }

    public Date getLastTimeLanded() {
        return lastTimeLanded;
    }
}
