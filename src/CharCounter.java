import java.util.Scanner;

/*
 * Auth: Dinh Vu 
 * Program that can read a sentence and character and then print out the total number of characters
 * also able to print out the how many times a specific character has been seen in the sentence.
 * And then show the index number that first time and last time the character occurrences.
 * 
 */
public class CharCounter {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter a sentence: ");
        String msg = scan.nextLine();

        // Check if the sentence is empty
        while (msg.isEmpty()) {
            System.out.println("The sentence cannot be empty.");
            msg = scan.nextLine();
        }

        System.out.println("Enter a character: ");
        String character = scan.nextLine();

        // Check if the character consists of only one character
        while (character.length() != 1) {
            System.out.println("The character must consist of only one character. Try again!: ");
            character = scan.nextLine();
        }
        // Variable initialization
        char c = character.charAt(0);
        int charCount = msg.length();
        int charOccurs = 0;
        int firstIndex = -1;
        int lastIndex = -1;

        // Count the number of occurrences and find the first and last index
        for (int i = 0; i < charCount; i++) {
            if (msg.charAt(i) == c) {
                charOccurs++;

                if (firstIndex == -1) {
                    firstIndex = i;
                }

                lastIndex = i;
            }
        }
        // Display output of respective tasks to the screen
        System.out.println("The sentence has a total of " + charCount + " " + "characters");
        System.out.println("The character " + character + " occurs: " + charOccurs + " times.");
        System.out.println("First time at index: " + firstIndex);
        System.out.println("Last time at index: " + lastIndex);
    }
}