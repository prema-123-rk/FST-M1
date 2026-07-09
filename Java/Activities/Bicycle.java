package activities;

public class Bicycle implements BicycleParts, BicycleOperations {

    private int speed;

    public Bicycle() {
        speed = 0;
    }

    @Override
    public void applyBrake(int decrement) {
        speed = speed - decrement;
        if (speed < 0) {
            speed = 0;
        }
        System.out.println("Speed after applying brake: " + speed);
    }

    @Override
    public void speedUp(int increment) {
        speed = speed + increment;
        if (speed > maxSpeed) {
            speed = maxSpeed;
        }
        System.out.println("Speed after speeding up: " + speed);
    }

    public void displayParts() {
        System.out.println("Number of tyres: " + tyres);
        System.out.println("Maximum speed: " + maxSpeed);
    }
}
