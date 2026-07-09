package activities;

public class Activity2 {

    public static void main(String[] args) {

        // Initialize array with 6 numbers
        int[] numbers = {10, 77, 10, 54, -11, 10};

        int sum = 0;

        // Find 10's in the array and add them
        for (int number : numbers) {
            if (number == 10) {
                sum += number;
            }
        }

        // Check if sum is equal to 30
        if (sum == 30) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
