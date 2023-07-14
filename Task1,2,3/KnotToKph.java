import java.util.Scanner; // Import the Scanner class
/*
 * Authors: Dinh Vu
 * Simple java class call "KnopToKph" that read data from user as knop and convert them into km/h
 * Program first created a scanner object.
 * and then take the user input then convert them to the decided format and display the result. 
 */
public class KnotToKph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Created a Scanner object
        System.err.println("Mata in hastighet i knop:"); // Display the msg to the console
        System.err.println(); // print empty line
        double knop = scanner.nextDouble(); // Read user input as double
        double kmph = knop * 1.852; // Convert method to km/h
        System.err.println(knop + " knop motsvarar" + " " + kmph + " km/h."); //Display the result in km/h

    }
}
