package activities;

public class Activity7 {

    public static void main(String[] args) {

        Bicycle bicycle = new Bicycle();

        bicycle.displayParts();

        bicycle.speedUp(30);
        bicycle.speedUp(25);
        bicycle.applyBrake(10);
    }
}
