package activities;

public class Activity1 {

    public static void main(String[] args) {

        Car car1 = new Car("Red", "Automatic", 2025);

        car1.displayCharacteristics();
        car1.accelerate();
        car1.brake();
    }
}
