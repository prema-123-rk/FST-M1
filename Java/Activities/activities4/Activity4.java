package activities;

import java.util.Arrays;

public class Activity4 {

    public static void main(String[] args) {

        int[] numbers = {4, 3, 2, 10, 12, 1, 5, 6};

        System.out.println("Original Array: " + Arrays.toString(numbers));

        insertionSort(numbers);

        System.out.println("Sorted Array: " + Arrays.toString(numbers));
    }

    // Insertion Sort algorithm
    public static void insertionSort(int[] array) {

        for (int i = 1; i < array.length; i++) {

            int key = array[i];
            int j = i - 1;

            // Move elements greater than key one position ahead
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }

            // Insert key at correct position
            array[j + 1] = key;
        }
    }
}
