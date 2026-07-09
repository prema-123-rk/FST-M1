package activities;

public class Car {

    String color;
    String transmission;
    int make;
    int tyres;
    int doors;

    public Car(String color, String transmission, int make) {
        this.color = color;
        this.transmission = transmission;
        this.make = make;
        this.tyres = 4;
        this.doors = 4;
    }

    public void displayCharacteristics() {
        System.out.println("Car Color: " + color);
        System.out.println("Transmission: " + transmission);
        System.out.println("Make: " + make);
        System.out.println("Number of Tyres: " + tyres);
        System.out.println("Number of Doors: " + doors);
    }

    public void accelerate() {
        System.out.println("Car is moving forward.");
    }

    public void brake() {
        System.out.println("Car has stopped.");
    }
}
