import java.util.Scanner;

/*
 * Auth: Dinh Vu
 * A simple program that can handle integers. It takes in a number of integers and prints the average value.
 * And how many of the entered numbers were entered only once
 * The program will also be able to handle incorrect input.
 */
public class AverageCalculator {
    /*
     * Main method that call all the other seperated methods in this class
     * Seperated methods makes code much easier to read, maintain and eventuelly debugging.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\t**** Integers Calculator ****");
        System.out.println();
        int count = readNumberOfIntegers(scanner);
        int[] inputNumbers = readNumbersFromUser(count, scanner);
        double average = averageCounter(inputNumbers);
        int numb = countNoRepeatedNumber(inputNumbers);

        System.out.println("You entered " + count + " integers");
        System.out.println("Out of those," + numb + " numbers appeared only once");
        System.out.println("The average value of the numbers is: " + average);

        scanner.close();

    }

    /**
     * Reads the number of integers to calculate from the user.
     *
     * @param scanner the Scanner object used for user input
     * @return the number of integers to calculate
     */
    public static int readNumberOfIntegers(Scanner scanner) {

        while (true) {
            System.out.printf("Enter the number of integers you want to calculate:");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Reads the specified number of integers from the user.
     *
     * @param count   The number of integers to read
     * @param scanner Scanner object to read user input
     * @return An array containing the entered integers
     */
    public static int[] readNumbersFromUser(int count, Scanner scanner) {

        int[] numbers = new int[count];

        for (int i = 0; i < count; i++) {
            int expectedNumber = i + 1;
            System.out.print("Enter number " + expectedNumber + ": ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.print("Enter number " + expectedNumber + ": ");
                scanner.next();
            }

            numbers[i] = scanner.nextInt();
        }

        return numbers;
    }

    /**
     * Calculates the average value of the given numbers.
     *
     * @param numbers An array of integers
     * @return The average value of the numbers
     */

    public static double averageCounter(int[] numbers) {
        if (numbers.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return (double) sum / numbers.length;

    }

    /**
     * Counts the number of unique numbers in an array.
     *
     * @param numbers An array of integers
     * @return The count of unique numbers in the array
     */
    public static int countNoRepeatedNumber(int[] numbers) {
        int nonRepeatedNumber = 0;
        for (int i = 0; i < numbers.length; i++) {
            boolean isUnique = true;
            for (int j = 0; j < numbers.length; j++) {
                if (i != j && numbers[i] == numbers[j]) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                nonRepeatedNumber++;
            }
        }
        return nonRepeatedNumber;
    }
}
