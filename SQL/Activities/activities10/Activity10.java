package activities;

import java.util.HashMap;
import java.util.Map;

public class Activity10 {

    public static void main(String[] args) {

        // Create Map with Integer keys and String values
        Map<Integer, String> colours = new HashMap<>();

        // Add 5 colours
        colours.put(1, "Red");
        colours.put(2, "Blue");
        colours.put(3, "Green");
        colours.put(4, "Yellow");
        colours.put(5, "Black");

        // Print Map
        System.out.println("Colours Map: " + colours);

        // Remove one colour
        colours.remove(4);
        System.out.println("After removing key 4: " + colours);

        // Check if green exists using containsValue()
        System.out.println("Contains Green? " + colours.containsValue("Green"));

        // Print size of Map
        System.out.println("Size of Map: " + colours.size());
    }
}
