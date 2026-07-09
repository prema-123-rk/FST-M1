package activities;

import java.util.HashSet;

public class Activity9 {

    public static void main(String[] args) {

        // Create a HashSet
        HashSet<String> hs = new HashSet<>();

        // Add 6 objects
        hs.add("Apple");
        hs.add("Banana");
        hs.add("Mango");
        hs.add("Orange");
        hs.add("Grapes");
        hs.add("Pineapple");

        // Print size of HashSet
        System.out.println("Size of HashSet: " + hs.size());

        // Remove an element
        hs.remove("Orange");
        System.out.println("After removing Orange: " + hs);

        // Try removing an element that is not present
        boolean removed = hs.remove("Watermelon");
        System.out.println("Trying to remove Watermelon: " + removed);

        // Check if item exists using contains()
        System.out.println("Contains Mango? " + hs.contains("Mango"));
        System.out.println("Contains Orange? " + hs.contains("Orange"));

        // Print updated set
        System.out.println("Updated HashSet: " + hs);
    }
}
