package activities;

import java.util.ArrayList;

public class Activity8 {

    public static void main(String[] args) {

        // Create ArrayList of type String
        ArrayList<String> myList = new ArrayList<String>();

        // Add 5 names
        myList.add("John");
        myList.add("Mary");
        myList.add("David");
        myList.add("Sarah");
        myList.add("Alex");

        // Print all names using for loop
        System.out.println("Names in ArrayList:");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }

        // Retrieve 3rd name using get()
        System.out.println("3rd name: " + myList.get(2));

        // Check if name exists using contains()
        System.out.println("Contains David? " + myList.contains("David"));

        // Print size of ArrayList
        System.out.println("Number of names: " + myList.size());

        // Remove a name using remove()
        myList.remove("Mary");

        // Print size after removal
        System.out.println("Number of names after removal: " + myList.size());
    }
}
