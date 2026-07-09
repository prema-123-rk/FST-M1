package activities;

public class Activity11 {

    public static void main(String[] args) {

        // Lambda expression without body
        Addable add1 = (num1, num2) -> num1 + num2;

        // Lambda expression with body
        Addable add2 = (num1, num2) -> {
            return num1 + num2;
        };

        int result1 = add1.add(10, 20);
        int result2 = add2.add(30, 40);

        System.out.println("Result using add1: " + result1);
        System.out.println("Result using add2: " + result2);
    }
}
