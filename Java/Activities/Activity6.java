package activities;

public class Activity6 {

    public static void main(String[] args) {

        Plane plane = new Plane(3);

        plane.addPassenger("John");
        plane.addPassenger("Mary");
        plane.addPassenger("David");

        System.out.println("Passengers: " + plane.getPassengers());

        plane.takeOff();
        plane.land();
    }
}
